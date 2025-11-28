package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.data.ResourceReader;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.data.InfusionMap;
import com.rodrigo.data.Ingredients;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
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
            ResourceReader.readResources(server);
            Registry<Enchantment> registry = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            for (Map.Entry<Item, RegistryKey<Enchantment>[]> entry : InfusionMap.CATALYSTS.entrySet()) {
                ResourceReader.addCatalysts(registry, entry.getKey(), entry.getValue());
            }
            String[] out = new String[]{"\n"};
            Ingredients.MAP.forEach((I, E) -> {
                out[0] = out[0].concat("\""+ Registries.ITEM.getId(I) + "\":{");
                for (StatusEffectInstance statusEffectInstance : E) {
                    out[0] = out[0].concat("\""+statusEffectInstance.getEffectType().getIdAsString() + "\":" + statusEffectInstance.getDuration() + ",");
                }
                out[0] = out[0].concat("}\n");
            });
            LOGGER.info(out[0]);
        }));
        LOGGER.info("Initialized");
    }
}