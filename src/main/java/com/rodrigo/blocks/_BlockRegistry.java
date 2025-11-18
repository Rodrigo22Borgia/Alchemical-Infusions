package com.rodrigo.blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.rodrigo.AlchemicalInfusions.modid;

public class _BlockRegistry {

    public static final Block alchemy_set = register("alchemy_set", AlchemyBlock::new, AbstractBlock.Settings.copy(Blocks.STONE), ItemGroups.FUNCTIONAL);
    public static final Block infusion_anvil = register("infusion_anvil", InfusionAnvil::new, AbstractBlock.Settings.copy(Blocks.ANVIL), ItemGroups.FUNCTIONAL);

    protected static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, RegistryKey<ItemGroup> groupKey) {
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, path));
        final Block block = Blocks.register(registryKey, factory, settings);
        final Item item = Items.register(block);
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(itemGroup -> itemGroup.add(item));
        return block;
    }

    public static void init() {}
}
