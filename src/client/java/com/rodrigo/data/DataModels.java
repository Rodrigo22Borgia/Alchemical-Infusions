package com.rodrigo.data;

import com.google.gson.JsonObject;
import com.rodrigo.AlchemicalInfusions;
import com.rodrigo.blocks.AlchemyBlock;
import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.MultipartModelConditionBuilder;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.apache.commons.compress.utils.Lists;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Quartet;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.rodrigo.AlchemicalInfusions.modid;
import static net.minecraft.client.data.BlockStateModelGenerator.*;

public class DataModels extends FabricModelProvider {
    public DataModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        multipart(generator, _BlockRegistry.alchemy_set, Identifier.of(modid, "block/alchemy"), new Pair[]{}, new Quartet[]{
                new Quartet<>(Identifier.of(modid, "block/alchemy_vial1"), AlchemyBlock.SLOT1, true, new Pair[]{}),
                new Quartet<>(Identifier.of(modid, "block/alchemy_vial2"), AlchemyBlock.SLOT2, true, new Pair[]{}),
                new Quartet<>(Identifier.of(modid, "block/alchemy_brew" ), AlchemyBlock.BREW , true, new Pair[]{}),
                new Quartet<>(Identifier.of(modid, "block/alchemy_fuel" ), AlchemyBlock.FUEL , true, new Pair[]{})
        });
        generator.registerItemModel(_ItemRegistry.splash_bottle);
        generator.registerItemModel(_ItemRegistry.lingering_bottle);



        generator.registerParentedItemModel(_BlockRegistry.infusion_anvil, Identifier.of(modid, "block/" + id(_BlockRegistry.infusion_anvil)));

        CreateVariants(generator, _BlockRegistry.infusion_anvil, BlockStateVariantMap.models(Properties.HORIZONTAL_FACING)
                .register(Direction.NORTH, modelOf(id(_BlockRegistry.infusion_anvil), false, 0, 0))
                .register(Direction.EAST , modelOf(id(_BlockRegistry.infusion_anvil), false, 90, 0))
                .register(Direction.SOUTH, modelOf(id(_BlockRegistry.infusion_anvil), false, 180, 0))
                .register(Direction.WEST , modelOf(id(_BlockRegistry.infusion_anvil), false, 270, 0))
        );
    }
    private static <T extends Comparable<T>> void multipart(BlockStateModelGenerator generator, Block block, Identifier parent, Pair<TextureKey, Identifier>[] main, Quartet<Identifier, Property<T>, T, Pair<TextureKey, Identifier>[]>[] parts) {
        Function<MultipartModelConditionBuilder, MultipartModelConditionBuilder> BUILDER = (builder) -> builder;
        String id = id(block);
        int i, j = 0;

        TextureMap textures = new TextureMap();
        TextureKey[] keys = new TextureKey[main.length];

        i = 0;
        for (final Pair<TextureKey, Identifier> pairs : main) {
            textures.put(pairs.getA(), pairs.getB());
            keys[i++] = pairs.getA();
        }

        MultipartBlockModelDefinitionCreator creator = MultipartBlockModelDefinitionCreator.create(block);
        creator.with(createWeightedVariant(new Model(Optional.of(parent), Optional.empty(), keys)
                .upload(Identifier.of(modid, id), textures, generator.modelCollector)));

        for (Quartet<Identifier, Property<T>, T, Pair<TextureKey, Identifier>[]> quartet : parts) {
            j++;
            textures = new TextureMap();
            keys = new TextureKey[quartet.getD().length];
            i = 0;
            for (final Pair<TextureKey, Identifier> pairs : quartet.getD()) {
                textures.put(pairs.getA(), pairs.getB());
                keys[i++] = pairs.getA();
            }
            creator.with(BUILDER.apply(BlockStateModelGenerator.createMultipartConditionBuilder().put(quartet.getB(), quartet.getC())),
                    createWeightedVariant(new Model(Optional.of(quartet.getA()), Optional.empty(), keys)
                            .upload(Identifier.of(modid, id + "_" + j), textures, generator.modelCollector)));
        }
        generator.blockStateCollector.accept(creator);
        generator.registerParentedItemModel(block, parent);
    }

    private static Model block(Identifier parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(parent), Optional.empty(), requiredTextureKeys);
    }

    public static void CreateVariants(BlockStateModelGenerator generator, Block block, BlockStateVariantMap map) {
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block).with(map));
    }

    public static WeightedVariant modelOf(String modelID) {
        return createWeightedVariant(Identifier.of(modid, "block/" + modelID));
    }
    public static List<WeightedVariant> modelOf(String modelID, int vars) {
        List<WeightedVariant> list = Lists.newArrayList();
        for (int i = 0; i < vars; i++) {
        //    list.add(WeightedVariant.create().put(VariantSettings.MODEL, Identifier.of(modid, modelID+"_"+i)));
        }
        return list;
    }

    public static WeightedVariant modelOf(String modelID, boolean UVlock, int rotateY, int rotateX) {
        WeightedVariant variant = modelOf(modelID);
        if (UVlock) {variant = variant.apply(UV_LOCK);}
        variant = switch (rotateY) {
            case 90 -> variant.apply(ROTATE_Y_90);
            case 180-> variant.apply(ROTATE_Y_180);
            case 270-> variant.apply(ROTATE_Y_270);
            default -> variant;
        };
        variant = switch (rotateX) {
                case 90 -> variant.apply(ROTATE_X_90);
                case 180-> variant.apply(ROTATE_X_180);
                case 270-> variant.apply(ROTATE_X_270);
                default -> variant;
        };
        return variant;
    }

    public static void applyTextures(BlockStateModelGenerator generator, String newJsonLoc, String parentLoc, String textureLoc) {
        generator.modelCollector.accept(Identifier.of(modid, newJsonLoc), () -> {
            JsonObject jsonObject = new JsonObject();
            JsonObject texture = new JsonObject();
            texture.addProperty("0",modid + ":" + textureLoc);
            texture.addProperty("particle",modid + ":" + textureLoc);
            jsonObject.addProperty("parent", modid + ":" + parentLoc);
            jsonObject.add("textures", texture);
            return jsonObject;
        });
    }

    public static void applyTextures(BlockStateModelGenerator generator, String newJsonLoc, String parentLoc, String... textures) {
        generator.modelCollector.accept(Identifier.of(modid, newJsonLoc), () -> {
            JsonObject jsonObject = new JsonObject();
            JsonObject texture = new JsonObject();
            if (textures.length != 0) {
                texture.addProperty("particle", modid + ":" + textures[0]);
                for (int i = 0; i < textures.length; i++) {
                    if (textures[i] == null) {
                        continue;
                    }
                    texture.addProperty(String.valueOf(i), modid + ":" + textures[i]);
                }
                jsonObject.add("textures", texture);
            }
            jsonObject.addProperty("parent", modid + ":" + parentLoc);
            return jsonObject;
        });
    }

    @SafeVarargs
    public static void applyTextures(BlockStateModelGenerator generator, String newJsonLoc, Identifier parentLoc, net.minecraft.util.Pair<String, Identifier>... textures) {
        generator.modelCollector.accept(Identifier.of(modid, newJsonLoc), () -> {
            JsonObject jsonObject = new JsonObject();
            JsonObject texture = new JsonObject();
            for (net.minecraft.util.Pair<String, Identifier> p: textures) {
                texture.addProperty(p.getLeft(),p.getRight().toString());
            }
            jsonObject.addProperty("parent", parentLoc.toString());
            jsonObject.add("textures", texture);
            return jsonObject;
        });
    }

    public static String id(Block block) {
        return Registries.BLOCK.getId(block).getPath().replaceFirst(".*\\.", "");
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
