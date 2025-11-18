package com.rodrigo.blocks;

import com.mojang.serialization.MapCodec;
import com.rodrigo.entities.AlchemyEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class AlchemyBlock extends BlockWithEntity  {
    public static final BooleanProperty FUEL = BooleanProperty.of("fuel");
    public static final BooleanProperty BREW = BooleanProperty.of("brew");
    public static final BooleanProperty SLOT1= BooleanProperty.of("slot1");
    public static final BooleanProperty SLOT2= BooleanProperty.of("slot2");
    public static final BooleanProperty LIT  = Properties.LIT;
    private static final BooleanProperty[] PROPERTIES = new BooleanProperty[]{BREW, SLOT1, SLOT2, FUEL};
    private static final VoxelShape SHAPE;

    public AlchemyBlock(Settings settings){
        super(settings);
        this.setDefaultState(getDefaultState().with(FUEL, false).with(BREW, false).with(SLOT1, false).with(SLOT2,false).with(LIT,false));
    }

    /**
     * Entity
     */

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlchemyEntity(pos, state);
    }

    /**
     * Interactions
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof final AlchemyEntity entity)) {
            return ActionResult.PASS;
        }
        final ItemStack inHand = player.getMainHandStack();

        PotionContentsComponent test = inHand.get(DataComponentTypes.POTION_CONTENTS);
        switch ((int) (hit.getPos().y % 1 * 1000)) {
            case 281-> {
                if (!entity.itemAssign(0, player, I -> {Item item = I.getItem();
                    return item == Items.GLASS_BOTTLE || item == Items.ARROW || item == Items.POTION;})) {return ActionResult.PASS;}
                ItemStack inSlot = entity.getStack(0);
                int slotCount = inSlot.getCount();
                if (slotCount > 1) {
                    ItemStack stack = player.getMainHandStack();
                    player.setStackInHand(Hand.MAIN_HAND, inSlot.copyWithCount(slotCount-1));
                    player.giveOrDropStack(stack);
                    entity.getStack(0).setCount(1);
                    entity.markDirty();
                }
                world.setBlockState(pos, state.with(BREW , !entity.getStack(0).isEmpty()));}
            case 62 -> {
                if (!entity.itemAssign(1, player, I -> I.getItem() != Items.TIPPED_ARROW && test != null && !test.customEffects().isEmpty())) {return ActionResult.PASS;}
                world.setBlockState(pos, state.with(SLOT1, !entity.getStack(1).isEmpty()));}
            case 56 -> {
                if (!entity.itemAssign(2, player, I -> I.getItem() != Items.TIPPED_ARROW && test != null && !test.customEffects().isEmpty())) {return ActionResult.PASS;}
                world.setBlockState(pos, state.with(SLOT2, !entity.getStack(2).isEmpty()));}
            case 50 -> {
                if (player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) {
                    inHand.damage(1, player);
                    world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS);
                    return brewPotion(entity, world, pos, state);}
                if (!entity.itemAssign(3, player, I -> I.getItem() == Items.STICK)) {return ActionResult.PASS;}
                world.setBlockState(pos, state.with(FUEL , !entity.getStack(3).isEmpty()));}
            default -> {return ActionResult.PASS;}
        }

        world.playSound(null, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 0.3f, 1f);
        world.updateListeners(pos, state, state, 0);
        return ActionResult.SUCCESS;
    }


    private static ActionResult brewPotion(AlchemyEntity entity, World world, BlockPos pos, BlockState state) {
        final ItemStack fuel = entity.getStack(3);
        if (fuel.getItem() != Items.STICK) {
            return ActionResult.PASS;
        }
        final ItemStack brew;
        final ItemStack slot0 = entity.getStack(0);
        final ItemStack slot1 = entity.getStack(1);
        final ItemStack slot2 = entity.getStack(2);
        final Item item0 = slot0.getItem();
        final Item item1 = slot1.getItem();
        final Item item2 = slot2.getItem();
        final List<StatusEffectInstance> ingredient1;
        final List<StatusEffectInstance> ingredient2;

        final PotionContentsComponent component1 = slot1.getComponents().get(DataComponentTypes.POTION_CONTENTS);
        final PotionContentsComponent component2 = slot2.getComponents().get(DataComponentTypes.POTION_CONTENTS);
        ingredient1 = component1 == null ? List.of() : component1.customEffects();
        ingredient2 = component2 == null ? List.of() : component2.customEffects();

        if (item0 == Items.ARROW) {
            brew = Items.TIPPED_ARROW.getDefaultStack();
            brew.set(DataComponentTypes.CUSTOM_NAME, Text.of("Tipped Arrow"));
        } else {
            brew = Items.POTION.getDefaultStack();
            brew.set(DataComponentTypes.CUSTOM_NAME, Text.of("Alchemical Brew"));
        }
//Tipping
        if (item0 == Items.ARROW) {if (!tip_arrow(item1, item2, brew, entity, ingredient1, ingredient2)) {return ActionResult.PASS;}}
        else if (item0 == Items.GLASS_BOTTLE) {
//CREATION
            if (!ingredient1.isEmpty() && !ingredient2.isEmpty() && item1 != item2) {
                createPotion(brew, slot1, slot2, ingredient1, ingredient2);

//COMBINATION
            } else if (item1 instanceof PotionItem && item2 instanceof PotionItem) {
                if (component1 == null || component2 == null) {
                    return ActionResult.PASS;
                }
                mixPotions(brew, entity, ingredient1, ingredient2);
            } else {return ActionResult.PASS;}
        }

        else if (item0 instanceof PotionItem) {
//PROLONGATION
            final PotionContentsComponent component = slot0.get(DataComponentTypes.POTION_CONTENTS);
            if (component == null) {
                return ActionResult.PASS;
            }

//DISTILLATION
            if (!prolongation(brew, item1, item2, component, ingredient1, ingredient2, slot1, slot2)) {
                distill(brew, component);
            }
        } else {return ActionResult.PASS;}

        entity.setStack(0, brew);
        fuel.decrement(1);
        world.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS);
        for (int i = 0; i < 4; i++) {
            state = state.with(PROPERTIES[i], !entity.getStack(i).isEmpty());
        }
        world.setBlockState(pos, state);
        entity.markDirty();
        world.updateListeners(pos, state, state, 0);
        return ActionResult.SUCCESS;
    }

    private static void distill(ItemStack brew, PotionContentsComponent component) {
        final StatusEffectInstance[] components = new StatusEffectInstance[component.customEffects().size()];
        int i = 0;

        //Increase amplifier, half time for each effect
        for (final StatusEffectInstance customEffect : component.customEffects()) {
            components[i++] = new StatusEffectInstance(customEffect.getEffectType(), customEffect.getDuration()/2, Math.min(5, customEffect.getAmplifier()+1));
        }
        applyBrewEffects(brew, components);
    }

    private static boolean prolongation(ItemStack brew, Item item1, Item item2, PotionContentsComponent component, List<StatusEffectInstance> ingredient1, List<StatusEffectInstance> ingredient2, ItemStack slot1, ItemStack slot2) {


        final List<StatusEffectInstance> iEffects = Stream.concat(
                ingredient1.isEmpty() || item1 instanceof PotionItem ? Stream.empty() : ingredient1.stream(),
                ingredient2.isEmpty() || item2 instanceof PotionItem ? Stream.empty() : ingredient2.stream()
        ).toList();


        boolean flag = false;
        Optional<Integer> prolongator;
        RegistryEntry<StatusEffect> type;
        int i = 0;
        final StatusEffectInstance[] array = new StatusEffectInstance[component.customEffects().size()];

        //Iterate through potion effects
        for (final StatusEffectInstance pEffect : component.customEffects()) {
            type = pEffect.getEffectType();

            //Find longest matching ingredient effect
            prolongator = iEffects.stream().filter(E -> E.getEffectType() == pEffect.getEffectType() && pEffect.getAmplifier() == 0).map(StatusEffectInstance::getDuration).max(Comparator.naturalOrder());

            //Apply longest effect time to potion
            if (prolongator.isPresent()) {
                final int a = pEffect.getDuration();
                final int b = prolongator.get();
                array[i] = new StatusEffectInstance(type, a + (b * b)/ a);
                flag = true;
            } else {
                array[i] = pEffect;
            }
            i++;
        }

        if (flag) {
            applyBrewEffects(brew, array);
            if (!(slot1.getItem() instanceof PotionItem)) {slot1.decrement(1);}
            if (!(slot2.getItem() instanceof PotionItem)) {slot2.decrement(1);}

        }
        return flag;
    }

    private static void mixPotions(ItemStack brew, AlchemyEntity entity, List<StatusEffectInstance> ingredient1, List<StatusEffectInstance> ingredient2) {
        final ArrayList<StatusEffectInstance> list = new ArrayList<>();

        for (final StatusEffectInstance effects1 : ingredient1) {
            final RegistryEntry<StatusEffect> type = effects1.getEffectType();
            final StatusEffectInstance[] temp = new StatusEffectInstance[1];
            list.addFirst(effects1);
            ingredient2.stream().filter(effects2 -> effects2.getEffectType() == type).forEach(effects2 -> {
                final int a = effects1.getAmplifier()+1;
                final int b = effects2.getAmplifier()+1;
                list.set(0,new StatusEffectInstance(type, (a*effects1.getDuration()+b*effects2.getDuration())/(a+b), Math.max(a,b)-1));
                temp[0] = effects2;
            });
            if(temp[0] != null) {
                ingredient2.remove(temp[0]);}
        }
        list.addAll(ingredient2);

        applyBrewEffects(brew, list.toArray(StatusEffectInstance[]::new));
        entity.setStack(1, new ItemStack(Items.GLASS_BOTTLE));
        entity.setStack(2, new ItemStack(Items.GLASS_BOTTLE));
    }

    private static void applyBrewEffects(final ItemStack brew, final StatusEffectInstance... effects) {
        PotionContentsComponent components = PotionContentsComponent.DEFAULT;
        for (final StatusEffectInstance effect : effects) {
            components = components.with(effect);
        }
        brew.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.POTION_CONTENTS, components).build());
    }

    private static void createPotion(ItemStack brew, ItemStack slot1, ItemStack slot2, List<StatusEffectInstance> ingredient1, List<StatusEffectInstance> ingredient2) {
        outer:
        //If two ingredient effects match, make potion
        for (final StatusEffectInstance effect1 : ingredient1) {
            for (final StatusEffectInstance effect2 : ingredient2) {
                if (effect1.getEffectType() == effect2.getEffectType()) {
                    applyBrewEffects(brew, new StatusEffectInstance(effect1.getEffectType(), (effect1.getDuration() + effect2.getDuration())/2));
                    break outer;
                }
            }
        }
        slot1.decrement(1);
        slot2.decrement(1);
    }

    private static boolean tip_arrow(Item item1, Item item2, ItemStack brew, AlchemyEntity entity, List<StatusEffectInstance> ingredient1, List<StatusEffectInstance> ingredient2) {
        if ((item1 != Items.POTION || ingredient1.isEmpty()) && (item2 != Items.POTION || ingredient2.isEmpty())) {
            return false;
        }
        final ArrayList<StatusEffectInstance> list = new ArrayList<>();

        for (final StatusEffectInstance effects1 : ingredient1) {
            final RegistryEntry<StatusEffect> type = effects1.getEffectType();
            final StatusEffectInstance[] temp = new StatusEffectInstance[1];
            list.addFirst(effects1);
            ingredient2.stream().filter(effects2 -> effects2.getEffectType() == type).forEach(effects2 -> {
                final int a = effects1.getAmplifier()+1;
                final int b = effects2.getAmplifier()+1;
                list.set(0,new StatusEffectInstance(type, (a*effects1.getDuration()+b*effects2.getDuration())/(a+b), Math.max(a,b)-1));
                temp[0] = effects2;
            });
            if(temp[0] != null) {
                ingredient2.remove(temp[0]);}
        }
        list.addAll(ingredient2);

        applyBrewEffects(brew, list.stream().map(effect -> new StatusEffectInstance(effect.getEffectType(), effect.getDuration()*8, effect.getAmplifier())).toArray(StatusEffectInstance[]::new));
        if (item1 instanceof PotionItem) {entity.setStack(1, new ItemStack(Items.GLASS_BOTTLE));}
        if (item2 instanceof PotionItem) {entity.setStack(2, new ItemStack(Items.GLASS_BOTTLE));}
        return true;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(AlchemyBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BREW, FUEL, SLOT1, SLOT2, LIT);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private static final HashMap<Item, StatusEffectInstance[]> INGREDIENT_MAP = new HashMap<>();
    static {
        INGREDIENT_MAP.put(Items.APPLE, new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.SATURATION, 1500), new StatusEffectInstance(StatusEffects.REGENERATION, 200)});
        INGREDIENT_MAP.put(Items.SWEET_BERRIES, new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.SATURATION, 200), new StatusEffectInstance(StatusEffects.ABSORPTION, 200)});
        INGREDIENT_MAP.put(Items.GOLDEN_APPLE, new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.ABSORPTION, 500)});

        SHAPE = VoxelShapes.union(
                VoxelShapes.cuboid(0.0625, 0, 0.3125, 0.4375, 0.05625, 0.6875),
                VoxelShapes.cuboid(0.5625, 0, 0.5625, 0.9375, 0.05, 0.9375),
                VoxelShapes.cuboid(0.5, 0, 0.0625, 0.875, 0.0625, 0.4375),
                VoxelShapes.cuboid(0.5625, 0.21875, 0.5625, 0.9375, 0.28125, 0.9375)
        );
    }
}
