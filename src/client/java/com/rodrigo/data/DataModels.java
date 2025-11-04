package com.rodrigo.data;

import com.rodrigo.AlchemicalInfusions;
import com.rodrigo.blocks.AlchemyBlock;
import com.rodrigo.blocks._BlockRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.MultipartModelConditionBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Quartet;

import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.client.data.BlockStateModelGenerator.createWeightedVariant;

public class DataModels extends FabricModelProvider {
    public DataModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        multipart(generator, _BlockRegistry.alchemy, Identifier.of(AlchemicalInfusions.modid, "block/alchemy"), new Pair[]{}, new Quartet[]{
                new Quartet<>(Identifier.of(AlchemicalInfusions.modid, "block/alchemy_vial1"), AlchemyBlock.SLOT1, true, new Pair[]{}),
                new Quartet<>(Identifier.of(AlchemicalInfusions.modid, "block/alchemy_vial2"), AlchemyBlock.SLOT2, true, new Pair[]{}),
                new Quartet<>(Identifier.of(AlchemicalInfusions.modid, "block/alchemy_brew" ), AlchemyBlock.BREW , true, new Pair[]{}),
                new Quartet<>(Identifier.of(AlchemicalInfusions.modid, "block/alchemy_fuel" ), AlchemyBlock.FUEL , true, new Pair[]{})
        });


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
                .upload(Identifier.of(AlchemicalInfusions.modid, id), textures, generator.modelCollector)));

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
                            .upload(Identifier.of(AlchemicalInfusions.modid, id + "_" + j), textures, generator.modelCollector)));
        }
        generator.blockStateCollector.accept(creator);
        generator.registerParentedItemModel(block, parent);
    }

    public static String id(Block block) {
        return Registries.BLOCK.getId(block).getPath().replaceFirst(".*\\.", "");
    }

    private static TexturedModel.Factory texturedModelFactory(String parent) {
        return TexturedModel.makeFactory(TextureMap::texture, block(parent));
        //Models;
    }

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(AlchemicalInfusions.modid,"block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
