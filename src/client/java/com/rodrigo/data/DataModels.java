package com.rodrigo.data;

import com.rodrigo.blocks._BlockRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ModelIds;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import static net.minecraft.client.data.BlockStateModelGenerator.createSingletonBlockState;

public class DataModels extends FabricModelProvider {
    public DataModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //blockStateModelGenerator.registerStateWithModelReference(_BlockRegistry.alchemy, Blocks.BARRIER);
        blockStateModelGenerator.registerSimpleState(_BlockRegistry.alchemy);
    }
//
//    public static void singleton(BlockStateModelGenerator G, Block block, String id, Identifier model, Identifier texture) {
//        applyTextures(G, id, model, new Pair<>("0", texture));
//        G.blockStateCollector.accept(createSingletonBlockState(block, Identifier.of(modid, id)));
//        G.registerParentedItemModel(block, Identifier.of(modid, id));
//    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
