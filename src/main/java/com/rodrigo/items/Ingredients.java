package com.rodrigo.items;

import com.rodrigo.mixin.ComponentMixin;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import static net.minecraft.entity.effect.StatusEffects.*;

public class Ingredients {
    public static void init() {
addEffects(Items.AZALEA                , new StatusEffectInstance(DOLPHINS_GRACE     , 2000), new StatusEffectInstance(SATURATION         , 1400));
addEffects(Items.BAMBOO                , new StatusEffectInstance(CONDUIT_POWER      , 1400), new StatusEffectInstance(SPEED              , 2500));
addEffects(Items.BIG_DRIPLEAF          , new StatusEffectInstance(DARKNESS           , 1300), new StatusEffectInstance(SLOW_FALLING       , 2200));
addEffects(Items.BUSH                  , new StatusEffectInstance(GLOWING            , 1400), new StatusEffectInstance(RESISTANCE         , 1400));
addEffects(Items.CACTUS                , new StatusEffectInstance(SATURATION         , 2500), new StatusEffectInstance(INVISIBILITY       , 1300));
addEffects(Items.CACTUS_FLOWER         , new StatusEffectInstance(BAD_OMEN           , 2200), new StatusEffectInstance(HUNGER             , 2100));
addEffects(Items.CHORUS_FLOWER         , new StatusEffectInstance(LEVITATION         , 900) , new StatusEffectInstance(HEALTH_BOOST       , 2500));
addEffects(Items.DEAD_BUSH             , new StatusEffectInstance(NIGHT_VISION       , 1800), new StatusEffectInstance(LUCK               , 2400));
addEffects(Items.FERN                  , new StatusEffectInstance(SLOW_FALLING       , 2100), new StatusEffectInstance(HERO_OF_THE_VILLAGE, 1900));
addEffects(Items.FIREFLY_BUSH          , new StatusEffectInstance(BLINDNESS          , 2500), new StatusEffectInstance(CONDUIT_POWER      , 900));
addEffects(Items.FLOWERING_AZALEA      , new StatusEffectInstance(OOZING             , 1400), new StatusEffectInstance(BLINDNESS          , 2200));
addEffects(Items.HANGING_ROOTS         , new StatusEffectInstance(POISON             , 1400), new StatusEffectInstance(FIRE_RESISTANCE    , 1500));
addEffects(Items.LARGE_FERN            , new StatusEffectInstance(ABSORPTION         , 2200), new StatusEffectInstance(JUMP_BOOST         , 900));
addEffects(Items.LEAF_LITTER           , new StatusEffectInstance(RESISTANCE         , 1900), new StatusEffectInstance(POISON             , 900));
addEffects(Items.LILY_PAD              , new StatusEffectInstance(FIRE_RESISTANCE    , 1000), new StatusEffectInstance(INSTANT_DAMAGE     , 1100));
addEffects(Items.MANGROVE_PROPAGULE    , new StatusEffectInstance(INVISIBILITY       , 2000), new StatusEffectInstance(LEVITATION         , 2600));
addEffects(Items.MANGROVE_ROOTS        , new StatusEffectInstance(WATER_BREATHING    , 2100), new StatusEffectInstance(RAID_OMEN          , 1500));
addEffects(Items.MOSS_BLOCK            , new StatusEffectInstance(INSTANT_HEALTH     , 2000), new StatusEffectInstance(WITHER             , 1400));
addEffects(Items.MOSS_CARPET           , new StatusEffectInstance(WEAVING            , 900) , new StatusEffectInstance(WATER_BREATHING    , 1400));
addEffects(Items.PALE_HANGING_MOSS     , new StatusEffectInstance(SLOWNESS           , 1200), new StatusEffectInstance(WEAKNESS           , 2100));
addEffects(Items.PALE_MOSS_BLOCK       , new StatusEffectInstance(SPEED              , 2200), new StatusEffectInstance(DARKNESS           , 1000));
addEffects(Items.PALE_MOSS_CARPET      , new StatusEffectInstance(REGENERATION       , 2600), new StatusEffectInstance(BAD_OMEN           , 1400));
addEffects(Items.PINK_PETALS           , new StatusEffectInstance(INFESTED           , 2500), new StatusEffectInstance(OOZING             , 2200));
addEffects(Items.PITCHER_PLANT         , new StatusEffectInstance(HUNGER             , 2500), new StatusEffectInstance(HASTE              , 1700));
addEffects(Items.SEAGRASS              , new StatusEffectInstance(MINING_FATIGUE     , 1500), new StatusEffectInstance(SLOWNESS           , 1600));
addEffects(Items.SHORT_DRY_GRASS       , new StatusEffectInstance(HASTE              , 1400), new StatusEffectInstance(NAUSEA             , 2500));
addEffects(Items.SHORT_GRASS           , new StatusEffectInstance(HEALTH_BOOST       , 2000), new StatusEffectInstance(INSTANT_HEALTH     , 2400));
addEffects(Items.SMALL_DRIPLEAF        , new StatusEffectInstance(TRIAL_OMEN         , 1300), new StatusEffectInstance(MINING_FATIGUE     , 1700));
addEffects(Items.SPORE_BLOSSOM         , new StatusEffectInstance(HERO_OF_THE_VILLAGE, 2000), new StatusEffectInstance(TRIAL_OMEN         , 2300));
addEffects(Items.TALL_DRY_GRASS        , new StatusEffectInstance(UNLUCK             , 2300), new StatusEffectInstance(WIND_CHARGED       , 2600));
addEffects(Items.TALL_GRASS            , new StatusEffectInstance(WITHER             , 1300), new StatusEffectInstance(DOLPHINS_GRACE     , 1000));
addEffects(Items.VINE                  , new StatusEffectInstance(LUCK               , 2600), new StatusEffectInstance(STRENGTH           , 1900));
addEffects(Items.WHEAT                 , new StatusEffectInstance(WEAKNESS           , 2100), new StatusEffectInstance(INFESTED           , 1700));
addEffects(Items.WILDFLOWERS           , new StatusEffectInstance(JUMP_BOOST         , 2200), new StatusEffectInstance(GLOWING            , 2600));

addEffects(Items.APPLE                 , new StatusEffectInstance(WIND_CHARGED       , 2100), new StatusEffectInstance(REGENERATION       , 1800));
addEffects(Items.BEETROOT              , new StatusEffectInstance(INSTANT_HEALTH     , 1300), new StatusEffectInstance(ABSORPTION         , 1000));
addEffects(Items.BEETROOT_SEEDS        , new StatusEffectInstance(OOZING             , 900) , new StatusEffectInstance(NIGHT_VISION       , 2100));
addEffects(Items.CARROT                , new StatusEffectInstance(FIRE_RESISTANCE    , 2400), new StatusEffectInstance(WEAVING            , 2400));
addEffects(Items.CHORUS_FRUIT          , new StatusEffectInstance(STRENGTH           , 1800), new StatusEffectInstance(LEVITATION         , 1500));
addEffects(Items.COCOA_BEANS           , new StatusEffectInstance(SLOW_FALLING       , 2100), new StatusEffectInstance(HEALTH_BOOST       , 2100));
addEffects(Items.GLOW_BERRIES          , new StatusEffectInstance(NIGHT_VISION       , 1100), new StatusEffectInstance(INFESTED           , 2500));
addEffects(Items.MELON_SEEDS           , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(INVISIBILITY       , 1800));
addEffects(Items.MELON_SLICE           , new StatusEffectInstance(HERO_OF_THE_VILLAGE, 1900), new StatusEffectInstance(HUNGER             , 1700));
addEffects(Items.PITCHER_POD           , new StatusEffectInstance(INFESTED           , 1000), new StatusEffectInstance(SLOW_FALLING       , 2000));
addEffects(Items.POISONOUS_POTATO      , new StatusEffectInstance(POISON             , 1800), new StatusEffectInstance(NAUSEA             , 2300));
addEffects(Items.POTATO                , new StatusEffectInstance(LEVITATION         , 2500), new StatusEffectInstance(INSTANT_HEALTH     , 1400));
addEffects(Items.PUMPKIN               , new StatusEffectInstance(SPEED              , 2300), new StatusEffectInstance(BLINDNESS          , 1500));
addEffects(Items.PUMPKIN_SEEDS         , new StatusEffectInstance(WATER_BREATHING    , 900) , new StatusEffectInstance(NIGHT_VISION       , 1100));
addEffects(Items.SUGAR_CANE            , new StatusEffectInstance(WITHER             , 2000), new StatusEffectInstance(WEAKNESS           , 1100));
addEffects(Items.SWEET_BERRIES         , new StatusEffectInstance(BLINDNESS          , 1900), new StatusEffectInstance(STRENGTH           , 1900));
addEffects(Items.TORCHFLOWER_SEEDS     , new StatusEffectInstance(CONDUIT_POWER      , 2300), new StatusEffectInstance(SLOWNESS           , 2300));
addEffects(Items.WHEAT_SEEDS           , new StatusEffectInstance(MINING_FATIGUE     , 1700), new StatusEffectInstance(SATURATION         , 1300));

addEffects(Items.DANDELION             , new StatusEffectInstance(SATURATION         , 1300), new StatusEffectInstance(REGENERATION       , 2100));
addEffects(Items.BLUE_ORCHID           , new StatusEffectInstance(SATURATION         , 1300), new StatusEffectInstance(NAUSEA             , 2100));
addEffects(Items.POPPY                 , new StatusEffectInstance(DOLPHINS_GRACE     , 1100), new StatusEffectInstance(NIGHT_VISION       , 1700));
addEffects(Items.TORCHFLOWER           , new StatusEffectInstance(GLOWING            , 1100), new StatusEffectInstance(NIGHT_VISION       , 1700));
addEffects(Items.ALLIUM                , new StatusEffectInstance(SATURATION         , 2000), new StatusEffectInstance(FIRE_RESISTANCE    , 1500));
addEffects(Items.AZURE_BLUET           , new StatusEffectInstance(BLINDNESS          , 1900), new StatusEffectInstance(LUCK               , 1400));
addEffects(Items.OPEN_EYEBLOSSOM       , new StatusEffectInstance(BLINDNESS          , 1900), new StatusEffectInstance(GLOWING            , 1400));
addEffects(Items.CLOSED_EYEBLOSSOM     , new StatusEffectInstance(NAUSEA             , 1900), new StatusEffectInstance(GLOWING            , 1400));
addEffects(Items.RED_TULIP             , new StatusEffectInstance(WEAKNESS           , 2500), new StatusEffectInstance(REGENERATION       , 1000));
addEffects(Items.ORANGE_TULIP          , new StatusEffectInstance(WEAKNESS           , 900) , new StatusEffectInstance(ABSORPTION         , 2600));
addEffects(Items.WHITE_TULIP           , new StatusEffectInstance(WEAKNESS           , 2200), new StatusEffectInstance(RAID_OMEN          , 2400));
addEffects(Items.PINK_TULIP            , new StatusEffectInstance(WEAKNESS           , 1100), new StatusEffectInstance(DARKNESS           , 1300));
addEffects(Items.WITHER_ROSE           , new StatusEffectInstance(WITHER             , 1100), new StatusEffectInstance(DARKNESS           , 1300));
addEffects(Items.OXEYE_DAISY           , new StatusEffectInstance(REGENERATION       , 1400), new StatusEffectInstance(BAD_OMEN           , 1200));
addEffects(Items.CORNFLOWER            , new StatusEffectInstance(JUMP_BOOST         , 2500), new StatusEffectInstance(MINING_FATIGUE     , 2100));
addEffects(Items.LILY_OF_THE_VALLEY    , new StatusEffectInstance(POISON             , 1900), new StatusEffectInstance(WATER_BREATHING    , 1200));

addEffects(Items.OAK_SAPLING           , new StatusEffectInstance(HASTE              , 1200), new StatusEffectInstance(OOZING             , 1500));
addEffects(Items.SPRUCE_SAPLING        , new StatusEffectInstance(INVISIBILITY       , 1900), new StatusEffectInstance(SPEED              , 1100));
addEffects(Items.BIRCH_SAPLING         , new StatusEffectInstance(WEAKNESS           , 1500), new StatusEffectInstance(HASTE              , 2200));
addEffects(Items.DARK_OAK_SAPLING      , new StatusEffectInstance(DARKNESS           , 1300), new StatusEffectInstance(INSTANT_DAMAGE     , 1500));
addEffects(Items.JUNGLE_SAPLING        , new StatusEffectInstance(GLOWING            , 2400), new StatusEffectInstance(CONDUIT_POWER      , 2500));
addEffects(Items.ACACIA_SAPLING        , new StatusEffectInstance(LUCK               , 1300), new StatusEffectInstance(RESISTANCE         , 2000));
addEffects(Items.CHERRY_SAPLING        , new StatusEffectInstance(ABSORPTION         , 900) , new StatusEffectInstance(FIRE_RESISTANCE    , 2100));
addEffects(Items.PALE_OAK_SAPLING      , new StatusEffectInstance(POISON             , 1300), new StatusEffectInstance(TRIAL_OMEN         , 2500));

addEffects(Items.COD                   , new StatusEffectInstance(TRIAL_OMEN         , 2000), new StatusEffectInstance(POISON             , 2300));
addEffects(Items.SALMON                , new StatusEffectInstance(INSTANT_DAMAGE     , 1600), new StatusEffectInstance(JUMP_BOOST         , 2500));
addEffects(Items.PUFFERFISH            , new StatusEffectInstance(DOLPHINS_GRACE     , 2100), new StatusEffectInstance(UNLUCK             , 1300));
addEffects(Items.RABBIT                , new StatusEffectInstance(INFESTED           , 2400), new StatusEffectInstance(WEAVING            , 2500));
addEffects(Items.RABBIT_FOOT           , new StatusEffectInstance(JUMP_BOOST         , 2400), new StatusEffectInstance(LUCK               , 2000));
addEffects(Items.RABBIT_HIDE           , new StatusEffectInstance(HASTE              , 1200), new StatusEffectInstance(WITHER             , 1300));
addEffects(Items.LEATHER               , new StatusEffectInstance(RESISTANCE         , 2500), new StatusEffectInstance(ABSORPTION         , 1500));
addEffects(Items.BEEF                  , new StatusEffectInstance(BLINDNESS          , 1600), new StatusEffectInstance(HUNGER             , 1800));
addEffects(Items.CHICKEN               , new StatusEffectInstance(MINING_FATIGUE     , 2500), new StatusEffectInstance(HUNGER             , 1200));
addEffects(Items.MUTTON                , new StatusEffectInstance(REGENERATION       , 1000), new StatusEffectInstance(HUNGER             , 900));
addEffects(Items.ROTTEN_FLESH          , new StatusEffectInstance(SPEED              , 900) , new StatusEffectInstance(HUNGER             , 2100));
addEffects(Items.SPIDER_EYE            , new StatusEffectInstance(NIGHT_VISION       , 1900), new StatusEffectInstance(POISON             , 1300));
addEffects(Items.TROPICAL_FISH         , new StatusEffectInstance(JUMP_BOOST         , 1200), new StatusEffectInstance(HEALTH_BOOST       , 2500));
addEffects(Items.HONEYCOMB             , new StatusEffectInstance(UNLUCK             , 2600), new StatusEffectInstance(ABSORPTION         , 1200));
addEffects(Items.STRING                , new StatusEffectInstance(SLOWNESS           , 1800), new StatusEffectInstance(INFESTED           , 1900));
addEffects(Items.FEATHER               , new StatusEffectInstance(LEVITATION         , 2600), new StatusEffectInstance(SLOW_FALLING       , 1700));
addEffects(Items.EGG                   , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(HEALTH_BOOST       , 2300));
addEffects(Items.PHANTOM_MEMBRANE      , new StatusEffectInstance(SLOW_FALLING       , 2300), new StatusEffectInstance(NIGHT_VISION       , 1600));
addEffects(Items.ARMADILLO_SCUTE       , new StatusEffectInstance(ABSORPTION         , 1700), new StatusEffectInstance(JUMP_BOOST         , 1500));
addEffects(Items.TURTLE_SCUTE          , new StatusEffectInstance(SLOWNESS           , 1600), new StatusEffectInstance(RESISTANCE         , 1400));
addEffects(Items.SLIME_BALL            , new StatusEffectInstance(JUMP_BOOST         , 1000), new StatusEffectInstance(OOZING             , 1200));
addEffects(Items.BLAZE_POWDER          , new StatusEffectInstance(FIRE_RESISTANCE    , 1100), new StatusEffectInstance(STRENGTH           , 900));
addEffects(Items.WIND_CHARGE           , new StatusEffectInstance(WIND_CHARGED       , 1900), new StatusEffectInstance(LEVITATION         , 900));
addEffects(Items.GUNPOWDER             , new StatusEffectInstance(FIRE_RESISTANCE    , 1100), new StatusEffectInstance(RAID_OMEN          , 1000));
addEffects(Items.PRISMARINE_CRYSTALS   , new StatusEffectInstance(NIGHT_VISION       , 1100), new StatusEffectInstance(DOLPHINS_GRACE     , 1400));
addEffects(Items.GHAST_TEAR            , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(WATER_BREATHING    , 2300));
addEffects(Items.BONE_MEAL             , new StatusEffectInstance(HEALTH_BOOST       , 1400), new StatusEffectInstance(POISON             , 1700));

addEffects(Items.REDSTONE              , new StatusEffectInstance(SLOWNESS           , 1500), new StatusEffectInstance(WEAKNESS           , 1200));
addEffects(Items.GLOWSTONE_DUST        , new StatusEffectInstance(GLOWING            , 1700), new StatusEffectInstance(SPEED              , 1500));
addEffects(Items.SUGAR                 , new StatusEffectInstance(SPEED              , 1800), new StatusEffectInstance(SLOWNESS           , 1300));
addEffects(Items.RESIN_CLUMP           , new StatusEffectInstance(CONDUIT_POWER      , 2200), new StatusEffectInstance(REGENERATION       , 1000));
addEffects(Items.GLISTERING_MELON_SLICE, new StatusEffectInstance(INSTANT_HEALTH     , 2200), new StatusEffectInstance(REGENERATION       , 1000));
addEffects(Items.FERMENTED_SPIDER_EYE  , new StatusEffectInstance(WEAKNESS           , 2200), new StatusEffectInstance(NAUSEA             , 1000));

addEffects(Items.CRIMSON_FUNGUS        , new StatusEffectInstance(INVISIBILITY       , 1500), new StatusEffectInstance(RESISTANCE         , 1800));
addEffects(Items.WARPED_FUNGUS         , new StatusEffectInstance(STRENGTH           , 2300), new StatusEffectInstance(INVISIBILITY       , 1500));
addEffects(Items.BROWN_MUSHROOM        , new StatusEffectInstance(POISON             , 2500), new StatusEffectInstance(SATURATION         , 900));
addEffects(Items.RED_MUSHROOM          , new StatusEffectInstance(WITHER             , 1600), new StatusEffectInstance(SATURATION         , 1700));
addEffects(Items.NETHER_SPROUTS        , new StatusEffectInstance(SLOWNESS           , 2000), new StatusEffectInstance(SLOW_FALLING       , 900));
addEffects(Items.CRIMSON_ROOTS         , new StatusEffectInstance(WEAVING            , 2600), new StatusEffectInstance(INFESTED           , 2400));
addEffects(Items.WARPED_ROOTS          , new StatusEffectInstance(INSTANT_DAMAGE     , 1800), new StatusEffectInstance(GLOWING            , 1100));
addEffects(Items.TWISTING_VINES        , new StatusEffectInstance(WITHER             , 2200), new StatusEffectInstance(MINING_FATIGUE     , 1900));
addEffects(Items.WEEPING_VINES         , new StatusEffectInstance(WATER_BREATHING    , 1100), new StatusEffectInstance(OOZING             , 2400));
addEffects(Items.NETHER_WART           , new StatusEffectInstance(INSTANT_HEALTH     , 1300), new StatusEffectInstance(NAUSEA             , 1800));
    }

    private static void addEffects(final Item ingredient, final StatusEffectInstance... effects) {
        PotionContentsComponent components = PotionContentsComponent.DEFAULT;
        for (final StatusEffectInstance effect : effects) {
            components = components.with(effect);
        }
        addComponents(ingredient, new Pair<>(DataComponentTypes.POTION_CONTENTS, components));
    }

    @SafeVarargs
    public static <T> void addComponents(Item item, Pair<ComponentType<T>, @Nullable T>... components) {
        ComponentMap.Builder builder = ComponentMap.builder().addAll(item.getComponents());
        for (Pair<ComponentType<T>, @Nullable T> component : components) {
            builder.add(component.getA(), component.getB());
        }
        ((ComponentMixin)item).setComponents(builder.build());
    }
}
