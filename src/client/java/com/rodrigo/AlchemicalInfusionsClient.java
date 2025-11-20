package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.entities.InfusionEntity;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.renderers.InfusionRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;

import java.util.Set;

public class AlchemicalInfusionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.putBlock(_BlockRegistry.alchemy_set, BlockRenderLayer.CUTOUT);
        BlockEntityRendererFactories.register(_EntityRegistry.infusionEntity, InfusionRenderer::new);
	}
}