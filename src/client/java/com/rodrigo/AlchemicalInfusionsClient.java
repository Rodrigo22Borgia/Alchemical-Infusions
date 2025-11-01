package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;

public class AlchemicalInfusionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.putBlock(_BlockRegistry.alchemy, BlockRenderLayer.CUTOUT);
	}
}