package com.rodrigo.blocks;

import com.mojang.serialization.MapCodec;
import com.rodrigo.entities.InfusionEntity;
import com.rodrigo.items.InfusionMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class InfusionAnvil extends BlockWithEntity {
    private static final VoxelShape[] SHAPE = new VoxelShape[4];
    private static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    private static final RegistryKey<Enchantment>[] EMPTY = new RegistryKey[0];

    public InfusionAnvil(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof InfusionEntity entity)) {return ActionResult.PASS;}
        if (entity.itemAssign(0, player, I -> I.isEnchantable() || I.hasEnchantments())) {
            world.playSound(null, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 0.3f, 1f);
            return ActionResult.SUCCESS;
        }
        final ItemStack stack = entity.getStack(0);
        final ItemStack hand  = player.getMainHandStack();
        if (player.getMainHandStack().getItem() instanceof PotionItem) {
            player.setStackInHand(Hand.MAIN_HAND, Items.GLASS_BOTTLE.getDefaultStack());

            final ArrayList<RegistryKey<Enchantment>> list = new ArrayList<>();
            hand.getComponents().get(DataComponentTypes.POTION_CONTENTS).getEffects().forEach(e -> java.util.Collections.addAll(list, InfusionMap.MAP.getOrDefault(e.getEffectType(), EMPTY)));
            Registry<Enchantment> registry = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);

            for (RegistryKey<Enchantment> enchant : list) {
                Enchantment e = registry.get(enchant);
                e.isAcceptableItem(stack);
                stack.addEnchantment(registry.getEntry(e), 1);
            }
            //EnchantmentHelper.apply(this, (builder) -> builder.add(enchantment, level));
        }
        return ActionResult.PASS;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE[state.get(FACING).getHorizontalQuarterTurns()];
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().rotateYCounterclockwise());
    }

    static {
        SHAPE[2]= VoxelShapes.union(
                VoxelShapes.cuboid(0.3125, 0     , 0.15625, 0.6875, 0.125 , 0.84375),
                VoxelShapes.cuboid(0.25  , 0.5625, 0.125  , 0.75  , 0.6875, 1),
                VoxelShapes.cuboid(0.375 , 0.125 , 0.25   , 0.625 , 0.25  , 0.75),
                VoxelShapes.cuboid(0.375 , 0.4375, 0.1875 , 0.625 , 0.5625, 0.8125),
                VoxelShapes.cuboid(0.375 , 0.5625, 0      , 0.625 , 0.6875, 0.125),
                VoxelShapes.cuboid(0.375 , 0.25  , 0.375  , 0.625 , 0.4375, 0.625)
        );
        SHAPE[3]= VoxelTransform.rotate90(SHAPE[2]);
        SHAPE[0]= VoxelTransform.rotate90(SHAPE[3]);
        SHAPE[1]= VoxelTransform.rotate90(SHAPE[0]);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InfusionEntity(pos,state);
    }
}
