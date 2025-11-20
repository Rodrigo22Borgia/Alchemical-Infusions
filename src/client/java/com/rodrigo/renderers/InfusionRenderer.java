package com.rodrigo.renderers;

import com.rodrigo.entities.InfusionEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class InfusionRenderer implements BlockEntityRenderer<InfusionEntity, InfusionState> {
    public final BlockEntityRendererFactory.Context ctx;
    public final ItemModelManager manager;

    public InfusionRenderer(BlockEntityRendererFactory.Context ctx) {
        this.ctx = ctx;
        this.manager = ctx.itemModelManager();
    }

    @Override
    public InfusionState createRenderState() {
        return new InfusionState();
    }

    @Override
    public void updateRenderState(InfusionEntity blockEntity, InfusionState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        final World world = blockEntity.getWorld();
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        manager.clearAndUpdate(state.renderState, blockEntity.getStack(0), ItemDisplayContext.FIXED, world, null, (int)blockEntity.getPos().asLong());
        final BlockState bs = world.getBlockState(blockEntity.getPos());
        if (blockEntity.getStack(0).isEmpty() || bs.getBlock() instanceof AirBlock) {state.noItem = true; return;}
        state.facing = bs.get(Properties.HORIZONTAL_FACING);
        state.noItem = false;
        state.light = world.getLightLevel(blockEntity.getPos());
    }

    @Override
    public void render(InfusionState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        if (state.noItem) {return;}
        matrices.translate(0.5f, 0.71f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.facing.getHorizontalQuarterTurns()*90-45));
        matrices.scale(3/4f, 3/4f, 3/4f);

       state.renderState.render(matrices, queue, state.light*17, OverlayTexture.DEFAULT_UV, 0);
    }
}