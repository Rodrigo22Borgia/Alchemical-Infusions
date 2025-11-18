package com.rodrigo.items;

import com.rodrigo.AlchemicalInfusions;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class _ItemRegistry {
    public static final Item splash_bottle = register("splash_bottle", Item::new, new Item.Settings(), ItemGroups.FOOD_AND_DRINK);
    public static final Item lingering_bottle = register("lingering_bottle", Item::new, new Item.Settings(), ItemGroups.FOOD_AND_DRINK);

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlchemicalInfusions.modid, id));
    }
    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings, RegistryKey<ItemGroup> groupKey) {
        final Item item = Items.register(keyOf(id), factory, settings);
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(itemGroup -> itemGroup.add(item));
        return item;
    }

    public static void init() {}
}
