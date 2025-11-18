package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.items.Ingredients;
import com.rodrigo.items._ItemRegistry;
import com.rodrigo.mixin.ComponentMixin;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.util.tuples.Pair;

public class AlchemicalInfusions implements ModInitializer {
	public static final String modid = "alchemical-infusions";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(modid);

	@Override
	public void onInitialize() {
        _EntityRegistry.init();
        _BlockRegistry.init();
        _ItemRegistry.init();
        Ingredients.init();
		LOGGER.info("Initialized");

	}

}