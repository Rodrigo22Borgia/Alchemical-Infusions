package com.rodrigo.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;


import static net.minecraft.entity.effect.StatusEffects.*;
import static net.minecraft.enchantment.Enchantments.*;
import java.util.HashMap;

public abstract class InfusionMap {
    public static final HashMap<RegistryEntry<StatusEffect>, RegistryKey<Enchantment>[]> MAP = new HashMap<>();
    public static void init() {
        MAP.put(REGENERATION, new RegistryKey[]{AQUA_AFFINITY, EFFICIENCY});
    }
}
