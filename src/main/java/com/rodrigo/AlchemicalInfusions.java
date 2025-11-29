package com.rodrigo;

import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.data.ResourceReader;
import com.rodrigo.entities._EntityRegistry;
import com.rodrigo.data.InfusionMap;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AlchemicalInfusions implements ModInitializer {
    public static HashMap<RegistryEntry<StatusEffect>, Enchantment[]> INFUSION_MAP = null;
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
        InfusionMap.init();

        ServerLifecycleEvents.SERVER_STARTED.register((server -> {
            ResourceReader.readResources(server);
            Registry<Enchantment> registry = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            for (Map.Entry<Item, RegistryKey<Enchantment>[]> entry : InfusionMap.CATALYSTS.entrySet()) {
                ResourceReader.addCatalysts(registry, entry.getKey(), entry.getValue());
            }
//            Registry<Enchantment> r = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
//            String[] out = new String[]{"\n"};
//            InfusionMap.MAP.forEach((effect, enchant) -> {
//                out[0] = out[0].concat("\""+ Registries.STATUS_EFFECT.getId(effect.value()) + "\":[");
//                for (RegistryKey<Enchantment> e : enchant) {
//                    out[0] = out[0].concat("\""+ r.getId(r.get(e)) + "\",");
//                }
//                out[0] = out[0].concat("]\n");
//            });
//            LOGGER.info(out[0]);
        }));
        LOGGER.info("Initialized");
    }
}