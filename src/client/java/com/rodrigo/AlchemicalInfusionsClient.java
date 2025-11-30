package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.entities.AlchemyEntity;
import com.rodrigo.entities.InfusionEntity;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.renderers.InfusionRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.item.tint.PotionTintSource;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;

import java.util.Optional;
import java.util.Set;

public class AlchemicalInfusionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.putBlock(_BlockRegistry.alchemy_set, BlockRenderLayer.TRANSLUCENT);
        BlockEntityRendererFactories.register(_EntityRegistry.infusionEntity, InfusionRenderer::new);
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            Optional<AlchemyEntity> optional = blockAndTintGetter.getBlockEntity(blockPos, _EntityRegistry.alchemyEntity);

            if (optional.isPresent()) {
                AlchemyEntity entity = optional.get();
                PotionContentsComponent potionContentsComponent = entity.getStack(i).get(DataComponentTypes.POTION_CONTENTS);
                if (potionContentsComponent != null) {
                    return ColorHelper.fullAlpha(potionContentsComponent.getColor());
                }
            }
            return 6400681;
        }, _BlockRegistry.alchemy_set);
	}
}