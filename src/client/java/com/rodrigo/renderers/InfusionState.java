package com.rodrigo.renderers;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.Direction;

public class InfusionState extends BlockEntityRenderState {
    protected int light = 255;
    protected final ItemRenderState renderState = new ItemRenderState();
    protected Direction facing = null;
    protected boolean noItem = false;
}
