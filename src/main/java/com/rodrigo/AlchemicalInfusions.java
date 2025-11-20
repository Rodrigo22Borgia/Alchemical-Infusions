package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.items.InfusionMap;
import com.rodrigo.items.Ingredients;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        InfusionMap.init();
		LOGGER.info("Initialized");

	}

}