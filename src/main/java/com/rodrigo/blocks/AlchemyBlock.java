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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
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
        if (player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) {
            return brewPotion(entity, world, pos, state);
        }

        final ItemStack inHand = player.getMainHandStack();
        int i;
        switch ((int) (hit.getPos().y % 1 * 1000)) {
            case 281-> {i = 0; world.setBlockState(pos, state.with(BREW , !inHand.isEmpty()));}
            case 62 -> {i = 1; world.setBlockState(pos, state.with(SLOT1, !inHand.isEmpty()));}
            case 56 -> {i = 2; world.setBlockState(pos, state.with(SLOT2, !inHand.isEmpty()));}
            case 50 -> {i = 3; world.setBlockState(pos, state.with(FUEL , !inHand.isEmpty()));
                if (player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) {
                    return brewPotion(entity, world, pos, state);
                }}
            default -> {return ActionResult.PASS;}
        }
        world.playSound(null, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS);

        player.setStackInHand(Hand.MAIN_HAND, entity.getStack(i));
        entity.setStack(i, inHand);
        handleInteraction(entity, world, pos, state);
        return ActionResult.SUCCESS;
    }
    private ActionResult brewPotion(AlchemyEntity entity, World world, BlockPos pos, BlockState state) {
        final ItemStack fuel = entity.getStack(3);
        if (fuel.getItem() != Items.SHORT_GRASS) {
            return ActionResult.PASS;
        }
        final ItemStack brew = Items.POTION.getDefaultStack();
        final ItemStack slot0 = entity.getStack(0);
        final ItemStack slot1 = entity.getStack(1);
        final ItemStack slot2 = entity.getStack(2);
        final Item item1 = slot1.getItem();
        final Item item2 = slot2.getItem();
        final StatusEffectInstance[] ingredient1 = INGREDIENT_MAP.get(item1);
        final StatusEffectInstance[] ingredient2 = INGREDIENT_MAP.get(item2);

        if (slot0.getItem() == Items.GLASS_BOTTLE) {
//CREATION
            if (ingredient1 != null && ingredient2 != null && ingredient1 != ingredient2) {
                outer:
                //If two ingredient effects match, make potion
                for (final StatusEffectInstance effect1 : ingredient1) {
                    for (final StatusEffectInstance effect2 : ingredient2) {
                        if (effect1.getEffectType() == effect2.getEffectType()) {
                            createBrew(brew, new StatusEffectInstance(effect1.getEffectType(), effect1.getDuration() + effect2.getDuration()));
                            break outer;
                        }
                    }
                }
                slot1.decrement(1);
                slot2.decrement(1);
//COMBINATION
            } else if (item1 instanceof PotionItem && item2 instanceof PotionItem) {
                final PotionContentsComponent component1 = slot1.get(DataComponentTypes.POTION_CONTENTS);
                final PotionContentsComponent component2 = slot2.get(DataComponentTypes.POTION_CONTENTS);
                if (component1 == null || component2 == null) {
                    return ActionResult.PASS;
                }

                //
                final ArrayList<StatusEffectInstance> list = new ArrayList<>();
                final ArrayList<StatusEffectInstance> list2 = new ArrayList<>(component2.customEffects());

                for (final StatusEffectInstance effects1 : component1.customEffects()) {
                    final RegistryEntry<StatusEffect> type = effects1.getEffectType();
                    final StatusEffectInstance[] temp = new StatusEffectInstance[1];
                    list.addFirst(effects1);
                    list2.stream().filter(effects2 -> effects2.getEffectType() == type).forEach(effects2 -> {
                        final int a = effects1.getAmplifier()+1;
                        final int b = effects2.getAmplifier()+1;
                        list.set(0,new StatusEffectInstance(type, (a*effects1.getDuration()+b*effects2.getDuration())/(a+b), Math.max(a,b)-1));
                        temp[0] = effects2;
                    });
                    if(temp[0] != null) {list2.remove(temp[0]);}
                }
                list.addAll(list2);

                createBrew(brew, list.toArray(StatusEffectInstance[]::new));
                entity.setStack(1, new ItemStack(Items.GLASS_BOTTLE));
                entity.setStack(2, new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        else if (slot0.getItem() instanceof PotionItem) {
//PROLONGATION
            final PotionContentsComponent component = slot0.get(DataComponentTypes.POTION_CONTENTS);
            if (component == null) {
                return ActionResult.PASS;
            }

            final List<StatusEffectInstance> iEffects = Stream.concat(
                    ingredient1 == null ? Stream.empty() : Arrays.stream(ingredient1),
                    ingredient2 == null ? Stream.empty() : Arrays.stream(ingredient2)
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
                createBrew(brew, array);
                if (!(slot1.getItem() instanceof PotionItem)) {slot1.decrement(1);}
                if (!(slot2.getItem() instanceof PotionItem)) {slot2.decrement(1);}

//DISTILLATION
            } else {
                final StatusEffectInstance[] components = new StatusEffectInstance[component.customEffects().size()];
                i = 0;

                //Increase amplifier, half time for each effect
                for (final StatusEffectInstance customEffect : component.customEffects()) {
                    components[i++] = new StatusEffectInstance(customEffect.getEffectType(), customEffect.getDuration()/2, Math.min(5, customEffect.getAmplifier()+1));
                }
                createBrew(brew, components);
            }
        } else {return ActionResult.PASS;}

        entity.setStack(0, brew);
        fuel.decrement(1);
        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS);
        for (int i = 0; i < 4; i++) {
            state = state.with(PROPERTIES[i], !entity.getStack(i).isEmpty());
        }
        world.setBlockState(pos, state);
        handleInteraction(entity, world, pos, state);
        return ActionResult.CONSUME;
    }

    private static void createBrew(final ItemStack brew, final StatusEffectInstance... effects) {
        PotionContentsComponent components = PotionContentsComponent.DEFAULT;
        for (final StatusEffectInstance effect : effects) {
            components = components.with(effect);
        }
        brew.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.POTION_CONTENTS, components).build());
    }

    private void handleInteraction(AlchemyEntity entity, World world, BlockPos pos, BlockState state) {
        entity.markDirty();
        world.updateListeners(pos, state, state, 0);
        displayInteraction(world, pos);
    }

    private void displayInteraction(World world, BlockPos pos){
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.spawnParticles(ParticleTypes.POOF, pos.getX() + 0.5F, pos.getY() + 1.0, pos.getZ() + 0.5F, 7, 0.0F, 0.0F, 0.0F, 0.0F);
        }
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
