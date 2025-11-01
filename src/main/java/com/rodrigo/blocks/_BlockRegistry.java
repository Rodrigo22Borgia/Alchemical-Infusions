package com.rodrigo.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.rodrigo.AlchemicalInfusions.modid;

public class _BlockRegistry {

    public static final Block alchemy = register("alchemy", AlchemyBlock::new, AbstractBlock.Settings.copy(Blocks.STONE));

    protected static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, path));
        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void init() {}
}
