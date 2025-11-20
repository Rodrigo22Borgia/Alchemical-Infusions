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
        MAP.put(SPEED              , new RegistryKey[]{ SWIFT_SNEAK             , RIPTIDE         , QUICK_CHARGE});
        MAP.put(SLOWNESS           , new RegistryKey[]{ SILK_TOUCH});
        MAP.put(HASTE              , new RegistryKey[]{ EFFICIENCY});
        MAP.put(MINING_FATIGUE     , new RegistryKey[]{ FROST_WALKER});
        MAP.put(STRENGTH           , new RegistryKey[]{ SHARPNESS               , POWER});
        MAP.put(INSTANT_HEALTH     , new RegistryKey[]{ PROTECTION});
        MAP.put(INSTANT_DAMAGE     , new RegistryKey[]{ PIERCING                , IMPALING});
        MAP.put(JUMP_BOOST         , new RegistryKey[]{ DENSITY                 , SWEEPING_EDGE});
        MAP.put(NAUSEA             , new RegistryKey[]{ RIPTIDE});
        MAP.put(REGENERATION       , new RegistryKey[]{ SMITE});
        MAP.put(RESISTANCE         , new RegistryKey[]{ PROTECTION});
        MAP.put(FIRE_RESISTANCE    , new RegistryKey[]{ FIRE_PROTECTION});
        MAP.put(WATER_BREATHING    , new RegistryKey[]{ RESPIRATION});
        MAP.put(INVISIBILITY       , new RegistryKey[]{ VANISHING_CURSE});
        MAP.put(BLINDNESS          , new RegistryKey[]{ VANISHING_CURSE});
        MAP.put(NIGHT_VISION       , new RegistryKey[]{ PIERCING});
        MAP.put(HUNGER             , new RegistryKey[]{ VANISHING_CURSE});
        MAP.put(WEAKNESS           , new RegistryKey[]{ BREACH});
        MAP.put(POISON             , new RegistryKey[]{ THORNS});
        MAP.put(WITHER             , new RegistryKey[]{ THORNS});
        MAP.put(HEALTH_BOOST       , new RegistryKey[]{ UNBREAKING});
        MAP.put(ABSORPTION         , new RegistryKey[]{ PROJECTILE_PROTECTION});
        MAP.put(SATURATION         , new RegistryKey[]{ BLAST_PROTECTION});
        MAP.put(GLOWING            , new RegistryKey[]{ FLAME                   , FIRE_ASPECT});
        MAP.put(LEVITATION         , new RegistryKey[]{ SOUL_SPEED});
        MAP.put(LUCK               , new RegistryKey[]{ LOOTING                 , FORTUNE         , LUCK_OF_THE_SEA});
        MAP.put(UNLUCK             , new RegistryKey[]{ CHANNELING});
        MAP.put(SLOW_FALLING       , new RegistryKey[]{ FEATHER_FALLING});
        MAP.put(CONDUIT_POWER      , new RegistryKey[]{ RESPIRATION, DEPTH_STRIDER});
        MAP.put(DOLPHINS_GRACE     , new RegistryKey[]{ DEPTH_STRIDER});
        MAP.put(BAD_OMEN           , new RegistryKey[]{ BINDING_CURSE});
        MAP.put(HERO_OF_THE_VILLAGE, new RegistryKey[]{ LOYALTY});
        MAP.put(DARKNESS           , new RegistryKey[]{ SOUL_SPEED});
        MAP.put(TRIAL_OMEN         , new RegistryKey[]{ BINDING_CURSE});
        MAP.put(RAID_OMEN          , new RegistryKey[]{ BINDING_CURSE});
        MAP.put(WIND_CHARGED       , new RegistryKey[]{ WIND_BURST              , KNOCKBACK       , PUNCH});
        MAP.put(WEAVING            , new RegistryKey[]{ BANE_OF_ARTHROPODS});
        MAP.put(OOZING             , new RegistryKey[]{ BANE_OF_ARTHROPODS});
        MAP.put(INFESTED           , new RegistryKey[]{ LURE});
    }
}
