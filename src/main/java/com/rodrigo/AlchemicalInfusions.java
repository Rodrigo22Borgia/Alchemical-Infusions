package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.items.InfusionMap;
import com.rodrigo.items.Ingredients;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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

        ServerLifecycleEvents.SERVER_STARTED.register((server -> {
            Registry<Enchantment> registry = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            for (Map.Entry<Item, RegistryKey<Enchantment>[]> entry : InfusionMap.CATALYSTS.entrySet()) {
                AlchemicalInfusions.LOGGER.info(entry.getKey().toString());
                Ingredients.addEnchants(registry, entry.getKey(), entry.getValue());
            }
        }));
        LOGGER.info("Initialized");
    }
}