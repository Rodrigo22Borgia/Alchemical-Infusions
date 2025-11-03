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
addEffects(Items.AZALEA             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BAMBOO             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BIG_DRIPLEAF       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BUSH               , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CACTUS             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CACTUS_FLOWER      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CHORUS_FLOWER      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.DEAD_BUSH          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.FERN               , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.FIREFLY_BUSH       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.FLOWERING_AZALEA   , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.HANGING_ROOTS      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.LARGE_FERN         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.LEAF_LITTER        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.LILY_PAD           , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MANGROVE_PROPAGULE , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MANGROVE_ROOTS     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MOSS_BLOCK         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MOSS_CARPET        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PALE_HANGING_MOSS  , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PALE_MOSS_BLOCK    , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PALE_MOSS_CARPET   , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PINK_PETALS        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PITCHER_PLANT      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SEAGRASS           , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SHORT_DRY_GRASS    , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SHORT_GRASS        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SMALL_DRIPLEAF     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SPORE_BLOSSOM      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TALL_DRY_GRASS     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TALL_GRASS         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TORCHFLOWER        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.VINE               , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WHEAT              , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WILDFLOWERS        , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.APPLE              , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BEETROOT           , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BEETROOT_SEEDS     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CARROT             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CHORUS_FRUIT       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.COCOA_BEANS        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.GLOW_BERRIES       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MELON_SEEDS        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MELON_SLICE        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PITCHER_POD        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.POISONOUS_POTATO   , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.POTATO             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PUMPKIN            , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PUMPKIN_SEEDS      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SUGAR_CANE         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SWEET_BERRIES      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TORCHFLOWER_SEEDS  , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WHEAT_SEEDS        , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.DANDELION          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.POPPY              , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.ALLIUM             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.AZURE_BLUET        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RED_TULIP          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.ORANGE_TULIP       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WHITE_TULIP        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PINK_TULIP         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.OXEYE_DAISY        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CORNFLOWER         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.LILY_OF_THE_VALLEY , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.OAK_SAPLING        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SPRUCE_SAPLING     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BIRCH_SAPLING      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.DARK_OAK_SAPLING   , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.JUNGLE_SAPLING     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.ACACIA_SAPLING     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CHERRY_SAPLING     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PALE_OAK_SAPLING   , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.COD                , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SALMON             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PUFFERFISH         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RABBIT             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RABBIT_FOOT        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RABBIT_HIDE        , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.LEATHER            , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BEEF               , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CHICKEN            , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.MUTTON             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.ROTTEN_FLESH       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SPIDER_EYE         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TROPICAL_FISH      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.HONEYCOMB          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.STRING             , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.FEATHER            , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.EGG                , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PHANTOM_MEMBRANE   , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.ARMADILLO_SCUTE    , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TURTLE_SCUTE       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SLIME_BALL         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BLAZE_ROD          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BREEZE_ROD         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.GUNPOWDER          , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.PRISMARINE_CRYSTALS, new StatusEffectInstance(SATURATION, 20));
addEffects(Items.GHAST_TEAR         , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BONE_MEAL          , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.REDSTONE           , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.GLOWSTONE_DUST     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.SUGAR              , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RESIN_CLUMP        , new StatusEffectInstance(SATURATION, 20));

addEffects(Items.CRIMSON_FUNGUS     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WARPED_FUNGUS      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.BROWN_MUSHROOM     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.RED_MUSHROOM       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.NETHER_SPROUTS     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.CRIMSON_ROOTS      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WARPED_ROOTS       , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.TWISTING_VINES     , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.WEEPING_VINES      , new StatusEffectInstance(SATURATION, 20));
addEffects(Items.NETHER_WART        , new StatusEffectInstance(SATURATION, 20));
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
