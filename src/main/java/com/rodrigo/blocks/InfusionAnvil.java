package com.rodrigo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class InfusionAnvil extends Block {
    private static final VoxelShape SHAPE[] = new VoxelShape[4];
    private static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;

    public InfusionAnvil(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
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
                VoxelShapes.cuboid(0.3125, 0, 0.15625, 0.6875, 0.125, 0.84375),
                VoxelShapes.cuboid(0.25, 0.5625, 0.125, 0.75, 0.6875, 1),
                VoxelShapes.cuboid(0.375, 0.125, 0.25, 0.625, 0.25, 0.75),
                VoxelShapes.cuboid(0.375, 0.4375, 0.1875, 0.625, 0.5625, 0.8125),
                VoxelShapes.cuboid(0.375, 0.5625, 0, 0.625, 0.6875, 0.125),
                VoxelShapes.cuboid(0.375, 0.25, 0.375, 0.625, 0.4375, 0.625)
        );
        SHAPE[3]= VoxelTransform.rotate90(SHAPE[2]);
        SHAPE[0]= VoxelTransform.rotate90(SHAPE[3]);
        SHAPE[1]= VoxelTransform.rotate90(SHAPE[0]);
    }
}
