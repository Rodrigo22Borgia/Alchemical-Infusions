package com.rodrigo.data;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.*;

import static net.minecraft.entity.effect.StatusEffects.*;

public class Ingredients {
    public static final HashMap<Item, StatusEffectInstance[]> MAP = new HashMap<>();

    public static void init() {
toMAP(Items.AZALEA                , new StatusEffectInstance(DOLPHINS_GRACE     , 2000), new StatusEffectInstance(SATURATION         , 0));
toMAP(Items.BAMBOO                , new StatusEffectInstance(CONDUIT_POWER      , 1400), new StatusEffectInstance(SPEED              , 2500));
toMAP(Items.BIG_DRIPLEAF          , new StatusEffectInstance(DARKNESS           , 1300), new StatusEffectInstance(SLOW_FALLING       , 2200));
toMAP(Items.BUSH                  , new StatusEffectInstance(GLOWING            , 1400), new StatusEffectInstance(RESISTANCE         , 1400));
toMAP(Items.CACTUS                , new StatusEffectInstance(SATURATION         , 0), new StatusEffectInstance(INVISIBILITY       , 1300));
toMAP(Items.CACTUS_FLOWER         , new StatusEffectInstance(BAD_OMEN           , 2200), new StatusEffectInstance(HUNGER             , 2100));
toMAP(Items.CHORUS_FLOWER         , new StatusEffectInstance(LEVITATION         , 900) , new StatusEffectInstance(HEALTH_BOOST       , 2500));
toMAP(Items.DEAD_BUSH             , new StatusEffectInstance(NIGHT_VISION       , 1800), new StatusEffectInstance(LUCK               , 2400));
toMAP(Items.FERN                  , new StatusEffectInstance(SLOW_FALLING       , 2100), new StatusEffectInstance(HERO_OF_THE_VILLAGE, 1900));
toMAP(Items.FIREFLY_BUSH          , new StatusEffectInstance(BLINDNESS          , 2500), new StatusEffectInstance(CONDUIT_POWER      , 900));
toMAP(Items.FLOWERING_AZALEA      , new StatusEffectInstance(OOZING             , 1400), new StatusEffectInstance(BLINDNESS          , 2200));
toMAP(Items.HANGING_ROOTS         , new StatusEffectInstance(POISON             , 1400), new StatusEffectInstance(FIRE_RESISTANCE    , 1500));
toMAP(Items.LARGE_FERN            , new StatusEffectInstance(ABSORPTION         , 2200), new StatusEffectInstance(JUMP_BOOST         , 900));
toMAP(Items.LEAF_LITTER           , new StatusEffectInstance(RESISTANCE         , 1900), new StatusEffectInstance(POISON             , 900));
toMAP(Items.LILY_PAD              , new StatusEffectInstance(FIRE_RESISTANCE    , 1000), new StatusEffectInstance(INSTANT_DAMAGE     , 0));
toMAP(Items.MANGROVE_PROPAGULE    , new StatusEffectInstance(INVISIBILITY       , 2000), new StatusEffectInstance(LEVITATION         , 2600));
toMAP(Items.MANGROVE_ROOTS        , new StatusEffectInstance(WATER_BREATHING    , 2100), new StatusEffectInstance(RAID_OMEN          , 1500));
toMAP(Items.MOSS_BLOCK            , new StatusEffectInstance(INSTANT_HEALTH     , 0), new StatusEffectInstance(WITHER             , 1400));
toMAP(Items.MOSS_CARPET           , new StatusEffectInstance(WEAVING            , 900) , new StatusEffectInstance(WATER_BREATHING    , 1400));
toMAP(Items.PALE_HANGING_MOSS     , new StatusEffectInstance(SLOWNESS           , 1200), new StatusEffectInstance(WEAKNESS           , 2100));
toMAP(Items.PALE_MOSS_BLOCK       , new StatusEffectInstance(SPEED              , 2200), new StatusEffectInstance(DARKNESS           , 1000));
toMAP(Items.PALE_MOSS_CARPET      , new StatusEffectInstance(REGENERATION       , 2600), new StatusEffectInstance(BAD_OMEN           , 1400));
toMAP(Items.PINK_PETALS           , new StatusEffectInstance(INFESTED           , 2500), new StatusEffectInstance(OOZING             , 2200));
toMAP(Items.PITCHER_PLANT         , new StatusEffectInstance(HUNGER             , 2500), new StatusEffectInstance(HASTE              , 1700));
toMAP(Items.SEAGRASS              , new StatusEffectInstance(MINING_FATIGUE     , 1500), new StatusEffectInstance(SLOWNESS           , 1600));
toMAP(Items.SHORT_DRY_GRASS       , new StatusEffectInstance(HASTE              , 1400), new StatusEffectInstance(NAUSEA             , 2500));
toMAP(Items.SHORT_GRASS           , new StatusEffectInstance(HEALTH_BOOST       , 2000), new StatusEffectInstance(INSTANT_HEALTH     , 0));
toMAP(Items.SMALL_DRIPLEAF        , new StatusEffectInstance(TRIAL_OMEN         , 1300), new StatusEffectInstance(MINING_FATIGUE     , 1700));
toMAP(Items.SPORE_BLOSSOM         , new StatusEffectInstance(HERO_OF_THE_VILLAGE, 2000), new StatusEffectInstance(TRIAL_OMEN         , 2300));
toMAP(Items.TALL_DRY_GRASS        , new StatusEffectInstance(UNLUCK             , 2300), new StatusEffectInstance(WIND_CHARGED       , 2600));
toMAP(Items.TALL_GRASS            , new StatusEffectInstance(WITHER             , 1300), new StatusEffectInstance(DOLPHINS_GRACE     , 1000));
toMAP(Items.VINE                  , new StatusEffectInstance(LUCK               , 2600), new StatusEffectInstance(STRENGTH           , 1900));
toMAP(Items.WHEAT                 , new StatusEffectInstance(WEAKNESS           , 2100), new StatusEffectInstance(INFESTED           , 1700));
toMAP(Items.WILDFLOWERS           , new StatusEffectInstance(JUMP_BOOST         , 2200), new StatusEffectInstance(GLOWING            , 2600));

toMAP(Items.APPLE                 , new StatusEffectInstance(SPEED       , 80), new StatusEffectInstance(REGENERATION       , 40));
toMAP(Items.BEETROOT              , new StatusEffectInstance(INSTANT_HEALTH     , 0), new StatusEffectInstance(ABSORPTION         , 40));
toMAP(Items.BEETROOT_SEEDS        , new StatusEffectInstance(OOZING             , 900) , new StatusEffectInstance(NIGHT_VISION       , 2100));
toMAP(Items.CARROT                , new StatusEffectInstance(NIGHT_VISION    , 100), new StatusEffectInstance(WATER_BREATHING            , 600));
toMAP(Items.CHORUS_FRUIT          , new StatusEffectInstance(STRENGTH           , 40), new StatusEffectInstance(LEVITATION         , 600));
toMAP(Items.COCOA_BEANS           , new StatusEffectInstance(SLOW_FALLING       , 2100), new StatusEffectInstance(HEALTH_BOOST       , 2100));
toMAP(Items.GLOW_BERRIES          , new StatusEffectInstance(NIGHT_VISION       , 100), new StatusEffectInstance(GLOWING           , 40));
toMAP(Items.MELON_SEEDS           , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(INVISIBILITY       , 1800));
toMAP(Items.MELON_SLICE           , new StatusEffectInstance(HEALTH_BOOST, 20), new StatusEffectInstance(SPEED             , 100));
toMAP(Items.PITCHER_POD           , new StatusEffectInstance(INFESTED           , 1000), new StatusEffectInstance(SLOW_FALLING       , 2000));
toMAP(Items.POISONOUS_POTATO      , new StatusEffectInstance(POISON             , 100), new StatusEffectInstance(NAUSEA             , 100));
toMAP(Items.POTATO                , new StatusEffectInstance(SLOW_FALLING         , 40), new StatusEffectInstance(INSTANT_HEALTH     , 0));
toMAP(Items.PUMPKIN               , new StatusEffectInstance(SPEED              , 2300), new StatusEffectInstance(BLINDNESS          , 1500));
toMAP(Items.PUMPKIN_SEEDS         , new StatusEffectInstance(WATER_BREATHING    , 900) , new StatusEffectInstance(NIGHT_VISION       , 1100));
toMAP(Items.SUGAR_CANE            , new StatusEffectInstance(WITHER             , 2000), new StatusEffectInstance(WEAKNESS           , 1100));
toMAP(Items.SWEET_BERRIES         , new StatusEffectInstance(WATER_BREATHING          , 100), new StatusEffectInstance(STRENGTH           , 40));
toMAP(Items.TORCHFLOWER_SEEDS     , new StatusEffectInstance(CONDUIT_POWER      , 2300), new StatusEffectInstance(SLOWNESS           , 2300));
toMAP(Items.WHEAT_SEEDS           , new StatusEffectInstance(MINING_FATIGUE     , 1700), new StatusEffectInstance(SATURATION         , 0));

toMAP(Items.DANDELION             , new StatusEffectInstance(SATURATION         , 0), new StatusEffectInstance(REGENERATION       , 2100));
toMAP(Items.BLUE_ORCHID           , new StatusEffectInstance(SATURATION         , 0), new StatusEffectInstance(NAUSEA             , 2100));
toMAP(Items.POPPY                 , new StatusEffectInstance(DOLPHINS_GRACE     , 1100), new StatusEffectInstance(NIGHT_VISION       , 1700));
toMAP(Items.TORCHFLOWER           , new StatusEffectInstance(GLOWING            , 1100), new StatusEffectInstance(NIGHT_VISION       , 1700));
toMAP(Items.ALLIUM                , new StatusEffectInstance(SATURATION         , 0), new StatusEffectInstance(FIRE_RESISTANCE    , 1500));
toMAP(Items.AZURE_BLUET           , new StatusEffectInstance(BLINDNESS          , 1900), new StatusEffectInstance(LUCK               , 1400));
toMAP(Items.OPEN_EYEBLOSSOM       , new StatusEffectInstance(BLINDNESS          , 1900), new StatusEffectInstance(GLOWING            , 1400));
toMAP(Items.CLOSED_EYEBLOSSOM     , new StatusEffectInstance(NAUSEA             , 1900), new StatusEffectInstance(GLOWING            , 1400));
toMAP(Items.RED_TULIP             , new StatusEffectInstance(WEAKNESS           , 2500), new StatusEffectInstance(REGENERATION       , 1000));
toMAP(Items.ORANGE_TULIP          , new StatusEffectInstance(WEAKNESS           , 900) , new StatusEffectInstance(ABSORPTION         , 2600));
toMAP(Items.WHITE_TULIP           , new StatusEffectInstance(WEAKNESS           , 2200), new StatusEffectInstance(RAID_OMEN          , 2400));
toMAP(Items.PINK_TULIP            , new StatusEffectInstance(WEAKNESS           , 1100), new StatusEffectInstance(DARKNESS           , 1300));
toMAP(Items.WITHER_ROSE           , new StatusEffectInstance(WITHER             , 1100), new StatusEffectInstance(DARKNESS           , 1300));
toMAP(Items.OXEYE_DAISY           , new StatusEffectInstance(REGENERATION       , 1400), new StatusEffectInstance(BAD_OMEN           , 1200));
toMAP(Items.CORNFLOWER            , new StatusEffectInstance(JUMP_BOOST         , 2500), new StatusEffectInstance(MINING_FATIGUE     , 2100));
toMAP(Items.LILY_OF_THE_VALLEY    , new StatusEffectInstance(POISON             , 1900), new StatusEffectInstance(WATER_BREATHING    , 1200));

toMAP(Items.OAK_SAPLING           , new StatusEffectInstance(HASTE              , 1200), new StatusEffectInstance(OOZING             , 1500));
toMAP(Items.SPRUCE_SAPLING        , new StatusEffectInstance(INVISIBILITY       , 1900), new StatusEffectInstance(SPEED              , 1100));
toMAP(Items.BIRCH_SAPLING         , new StatusEffectInstance(WEAKNESS           , 1500), new StatusEffectInstance(HASTE              , 2200));
toMAP(Items.DARK_OAK_SAPLING      , new StatusEffectInstance(DARKNESS           , 1300), new StatusEffectInstance(INSTANT_DAMAGE     , 0));
toMAP(Items.JUNGLE_SAPLING        , new StatusEffectInstance(GLOWING            , 2400), new StatusEffectInstance(CONDUIT_POWER      , 2500));
toMAP(Items.ACACIA_SAPLING        , new StatusEffectInstance(LUCK               , 1300), new StatusEffectInstance(RESISTANCE         , 2000));
toMAP(Items.CHERRY_SAPLING        , new StatusEffectInstance(ABSORPTION         , 900) , new StatusEffectInstance(FIRE_RESISTANCE    , 2100));
toMAP(Items.PALE_OAK_SAPLING      , new StatusEffectInstance(POISON             , 1300), new StatusEffectInstance(TRIAL_OMEN         , 2500));

toMAP(Items.COD                   , new StatusEffectInstance(TRIAL_OMEN         , 40), new StatusEffectInstance(HASTE             , 100));
toMAP(Items.SALMON                , new StatusEffectInstance(DOLPHINS_GRACE     , 40), new StatusEffectInstance(JUMP_BOOST         , 40));
toMAP(Items.PUFFERFISH            , new StatusEffectInstance(POISON             , 400), new StatusEffectInstance(UNLUCK             , 100));
toMAP(Items.RABBIT                , new StatusEffectInstance(INFESTED           , 100), new StatusEffectInstance(WEAVING            , 100));
toMAP(Items.RABBIT_FOOT           , new StatusEffectInstance(JUMP_BOOST         , 2400), new StatusEffectInstance(LUCK               , 2000));
toMAP(Items.RABBIT_HIDE           , new StatusEffectInstance(HASTE              , 1200), new StatusEffectInstance(WITHER             , 1300));
toMAP(Items.LEATHER               , new StatusEffectInstance(RESISTANCE         , 2500), new StatusEffectInstance(ABSORPTION         , 1500));
toMAP(Items.BEEF                  , new StatusEffectInstance(BLINDNESS          , 200), new StatusEffectInstance(HUNGER             , 400));
toMAP(Items.CHICKEN               , new StatusEffectInstance(MINING_FATIGUE     , 200), new StatusEffectInstance(HUNGER             , 400));
toMAP(Items.MUTTON                , new StatusEffectInstance(WEAKNESS           , 200), new StatusEffectInstance(HUNGER             , 400));
toMAP(Items.ROTTEN_FLESH          , new StatusEffectInstance(POISON             , 200) , new StatusEffectInstance(HUNGER             , 800));
toMAP(Items.SPIDER_EYE            , new StatusEffectInstance(NIGHT_VISION       , 100), new StatusEffectInstance(POISON             , 100));
toMAP(Items.TROPICAL_FISH         , new StatusEffectInstance(JUMP_BOOST         , 40), new StatusEffectInstance(HEALTH_BOOST       , 40));
toMAP(Items.HONEYCOMB             , new StatusEffectInstance(UNLUCK             , 2600), new StatusEffectInstance(ABSORPTION         , 1200));
toMAP(Items.STRING                , new StatusEffectInstance(SLOWNESS           , 1800), new StatusEffectInstance(INFESTED           , 1900));
toMAP(Items.FEATHER               , new StatusEffectInstance(LEVITATION         , 2600), new StatusEffectInstance(SLOW_FALLING       , 1700));
toMAP(Items.EGG                   , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(HEALTH_BOOST       , 2300));
toMAP(Items.PHANTOM_MEMBRANE      , new StatusEffectInstance(SLOW_FALLING       , 2300), new StatusEffectInstance(NIGHT_VISION       , 1600));
toMAP(Items.ARMADILLO_SCUTE       , new StatusEffectInstance(ABSORPTION         , 1700), new StatusEffectInstance(JUMP_BOOST         , 1500));
toMAP(Items.TURTLE_SCUTE          , new StatusEffectInstance(SLOWNESS           , 1600), new StatusEffectInstance(RESISTANCE         , 1400));
toMAP(Items.SLIME_BALL            , new StatusEffectInstance(JUMP_BOOST         , 1000), new StatusEffectInstance(OOZING             , 1200));
toMAP(Items.BLAZE_POWDER          , new StatusEffectInstance(FIRE_RESISTANCE    , 1100), new StatusEffectInstance(STRENGTH           , 900));
toMAP(Items.WIND_CHARGE           , new StatusEffectInstance(WIND_CHARGED       , 1900), new StatusEffectInstance(LEVITATION         , 900));
toMAP(Items.GUNPOWDER             , new StatusEffectInstance(FIRE_RESISTANCE    , 1100), new StatusEffectInstance(RAID_OMEN          , 1000));
toMAP(Items.PRISMARINE_CRYSTALS   , new StatusEffectInstance(NIGHT_VISION       , 1100), new StatusEffectInstance(DOLPHINS_GRACE     , 1400));
toMAP(Items.GHAST_TEAR            , new StatusEffectInstance(REGENERATION       , 2300), new StatusEffectInstance(WATER_BREATHING    , 2300));
toMAP(Items.BONE_MEAL             , new StatusEffectInstance(HEALTH_BOOST       , 1400), new StatusEffectInstance(POISON             , 1700));
toMAP(Items.HONEY_BOTTLE          , new StatusEffectInstance(INSTANT_HEALTH     , 0),    new StatusEffectInstance(SATURATION         , 0));

toMAP(Items.REDSTONE              , new StatusEffectInstance(SLOWNESS           , 1500), new StatusEffectInstance(WEAKNESS           , 1200));
toMAP(Items.GLOWSTONE_DUST        , new StatusEffectInstance(GLOWING            , 1700), new StatusEffectInstance(SPEED              , 1500));
toMAP(Items.SUGAR                 , new StatusEffectInstance(SPEED              , 1800), new StatusEffectInstance(SLOWNESS           , 1300));
toMAP(Items.RESIN_CLUMP           , new StatusEffectInstance(CONDUIT_POWER      , 2200), new StatusEffectInstance(REGENERATION       , 1000));
toMAP(Items.GLISTERING_MELON_SLICE, new StatusEffectInstance(INSTANT_HEALTH     , 0), new StatusEffectInstance(REGENERATION       , 40));
toMAP(Items.FERMENTED_SPIDER_EYE  , new StatusEffectInstance(WEAKNESS           , 2200), new StatusEffectInstance(NAUSEA             , 1000));

toMAP(Items.CRIMSON_FUNGUS        , new StatusEffectInstance(INVISIBILITY       , 1500), new StatusEffectInstance(RESISTANCE         , 1800));
toMAP(Items.WARPED_FUNGUS         , new StatusEffectInstance(STRENGTH           , 2300), new StatusEffectInstance(INVISIBILITY       , 1500));
toMAP(Items.BROWN_MUSHROOM        , new StatusEffectInstance(POISON             , 2500), new StatusEffectInstance(SATURATION         , 0));
toMAP(Items.RED_MUSHROOM          , new StatusEffectInstance(WITHER             , 1600), new StatusEffectInstance(SATURATION         , 0));
toMAP(Items.NETHER_SPROUTS        , new StatusEffectInstance(SLOWNESS           , 2000), new StatusEffectInstance(SLOW_FALLING       , 900));
toMAP(Items.CRIMSON_ROOTS         , new StatusEffectInstance(WEAVING            , 2600), new StatusEffectInstance(INFESTED           , 2400));
toMAP(Items.WARPED_ROOTS          , new StatusEffectInstance(INSTANT_DAMAGE     , 0), new StatusEffectInstance(GLOWING            , 1100));
toMAP(Items.TWISTING_VINES        , new StatusEffectInstance(WITHER             , 2200), new StatusEffectInstance(MINING_FATIGUE     , 1900));
toMAP(Items.WEEPING_VINES         , new StatusEffectInstance(WATER_BREATHING    , 1100), new StatusEffectInstance(OOZING             , 2400));
toMAP(Items.NETHER_WART           , new StatusEffectInstance(INSTANT_HEALTH     , 0), new StatusEffectInstance(NAUSEA             , 1800));
    }

    private static void toMAP(final Item ingredient, final StatusEffectInstance... effects) {
        MAP.put(ingredient, effects);
    }
}
