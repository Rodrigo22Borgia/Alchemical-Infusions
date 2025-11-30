package com.rodrigo.blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.rodrigo.AlchemicalInfusions.modid;

public class _BlockRegistry {

    public static final Block infusion_anvil = register("infusion_anvil", InfusionAnvil::new, AbstractBlock.Settings.copy(Blocks.ANVIL),
            new Item.Settings().component(DataComponentTypes.LORE, LoreComponent.DEFAULT
                    .with(Text.of("§6Brew §7+ §fItem §7→ §6Enchanted §fItem"))
                    .with(Text.of("§6Catalyst §7+ §6Enchanted §fItem §7→ §cLevel §7↑"))
            ), ItemGroups.FUNCTIONAL);
    public static final Block alchemy_set = register("alchemy_set", AlchemyBlock::new, AbstractBlock.Settings.copy(Blocks.STONE),
            new Item.Settings().component(DataComponentTypes.LORE, LoreComponent.DEFAULT
                    .with(Text.of("§72 §6Ingredients §7with shared §6Effect §7→ New §6Brew"))
                    .with(Text.of("§cAmplifier §7'1' §6Brew §7+ §6Ingredient §7→ §fDuration §7↑"))
                    .with(Text.of("§eBrew §7+ §bBrew §7→ combined §aEffects §7& §fDuration"))
                    .with(Text.of("§7Reignite §6Brew §7→ 1/2 §fDuration§7, +1 §cAmplifier"))
            ), ItemGroups.FUNCTIONAL);
    protected static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, Item.Settings itemSettings, RegistryKey<ItemGroup> groupKey) {
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, path));
        final Block block = Blocks.register(registryKey, factory, settings);
        final Item item = Items.register(block, BlockItem::new, itemSettings);;
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(itemGroup -> itemGroup.add(item));
        return block;
    }

    public static void init() {

    }
}
