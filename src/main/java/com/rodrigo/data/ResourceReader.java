package com.rodrigo.data;

import com.rodrigo.AlchemicalInfusions;
import com.rodrigo.mixin.ComponentMixin;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.*;

public class ResourceReader {
    private static void addEffects(final Item ingredient, final StatusEffectInstance... effects) {
        PotionContentsComponent components = PotionContentsComponent.DEFAULT;
        for (final StatusEffectInstance effect : effects) {
            components = components.with(effect);
        }
        addComponents(ingredient, DataComponentTypes.POTION_CONTENTS, components);
    }

    @SafeVarargs
    public static void addCatalysts(Registry<Enchantment> registry, Item item, RegistryKey<Enchantment>... enchants) {
        LoreComponent lore = LoreComponent.DEFAULT;

        for (RegistryKey<Enchantment> enchant : enchants) {
            RegistryEntry<Enchantment> e = registry.getEntry(registry.get(enchant));
            lore = lore.with(Enchantment.getName(e, e.value().getMaxLevel()).copy().setStyle(Style.EMPTY.withColor(Formatting.GRAY).withItalic(false)));
        }

        addComponents(item, DataComponentTypes.LORE, lore);
    }

    public static void readResources(MinecraftServer server) {
        //Read all resources
        HashMap<Integer, JSONObject> catalystMap = new HashMap<>();
        HashMap<Integer, JSONObject> enchantMap = new HashMap<>();
        HashMap<Integer, JSONObject> ingredientMap = new HashMap<>();
        server.getResourceManager().findAllResources("alchemy", I -> I.getPath().equals("alchemy/infusions.json")).forEach((I, R) -> {
            try {
                JSONTokener reader =  new JSONTokener(R.getFirst().getReader());
                JSONObject json =  new JSONObject(reader);
                ingredientMap.put(json.getInt("priority"), json.getJSONObject("ingredients"));
                enchantMap   .put(json.getInt("priority"), json.getJSONObject("enchantments"));
                catalystMap  .put(json.getInt("priority"), json.getJSONObject("catalysts"));
                reader.close();
            } catch (IOException e) {
                AlchemicalInfusions.LOGGER.error("Invalid format in: {}", I.toString());
            }
        });
        readIngredients(ingredientMap);
        readEnchants(enchantMap);
        readCatalysts(catalystMap);
    }

    private static void readCatalysts(HashMap<Integer, JSONObject> catalystMap) {
    }

    private static void readEnchants(HashMap<Integer, JSONObject> enchantMap) {
//        enchantMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(
//                entry -> entry.getValue().keySet().forEach(key->))
    }

    private static void readIngredients(HashMap<Integer, JSONObject> ingredientMap) {
        //Sort by priority
        HashMap<String, JSONObject> idMap = new HashMap<>();
        ingredientMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(
                //Merge into single map of ID : EFFECTS
                entry -> entry.getValue().keySet().forEach(key -> idMap.put(key, entry.getValue().getJSONObject(key))));

        //Add merged maps effects to items
        Registry<Item> registry =  Registries.ITEM;
        idMap.forEach((id, effects)-> {
            ArrayList<StatusEffectInstance> list = new ArrayList<>();
            effects.keySet().forEach(key -> list.add(new StatusEffectInstance(Registries.STATUS_EFFECT.getEntry(Identifier.of(key)).get(),effects.getInt(key))));
            addEffects(registry.get(Identifier.of(id)), list.toArray(new StatusEffectInstance[0]));
        });

        //Add hardcoded effects, unless overwritten
//        Ingredients.MAP.forEach((item, effects) -> {
//            if (!idMap.containsKey(registry.getId(item).toString())) {
//                addEffects(item, effects);
//            }
//        });
    }

    public static <T> void addComponents(Item item, ComponentType<T> component, @Nullable T value) {
        ComponentMap.Builder builder = ComponentMap.builder().addAll(item.getComponents());
        builder.add(component, value);
        ((ComponentMixin)item).setComponents(builder.build());
    }
}
