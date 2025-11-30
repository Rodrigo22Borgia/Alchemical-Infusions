package com.rodrigo.data;

import com.rodrigo.AlchemicalInfusions;
import com.rodrigo.mixin.ComponentMixin;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import oshi.util.tuples.Pair;

import java.io.IOException;
import java.util.*;

public class ResourceReader {
    public static void readResources(MinecraftServer server) {
        HashMap<Integer, Pair<Identifier, JSONObject>> packMap = new HashMap<>();
        //Read all resources
        server.getResourceManager().findAllResources("alchemy", I -> I.getPath().equals("alchemy/infusions.json")).forEach((I, R) -> {
            try {
                JSONTokener reader =  new JSONTokener(R.getFirst().getReader());
                JSONObject json =  new JSONObject(reader);
                packMap.put(json.getInt("priority"), new Pair<>(I,json));
                reader.close();
            } catch (Exception e) {
                AlchemicalInfusions.LOGGER.error("Invalid format in \"{}\": {}", I.toString(), e.getMessage());
            }
        });

        HashMap<String, JSONArray> catalystMap = new HashMap<>();
        HashMap<String, JSONArray> enchantMap = new HashMap<>();
        HashMap<String, JSONObject> ingredientMap = new HashMap<>();
        //Sort by priority & map IDs
        packMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(entry -> {
            JSONObject object = entry.getValue().getB();
            if (object.has("ingredients")) {
                JSONObject ingredients = object.getJSONObject("ingredients");
                ingredients.keySet().forEach(key -> ingredientMap.put(key, ingredients.getJSONObject(key)));
            }
            if (object.has("enchantments")) {
                JSONObject enchants = object.getJSONObject("enchantments");
                enchants.keySet().forEach(key -> enchantMap.put(key, enchants.getJSONArray(key)));
            }
            if (object.has("catalysts")) {
                JSONObject catalysts = object.getJSONObject("catalysts");
                catalysts.keySet().forEach(key -> catalystMap.put(key, catalysts.getJSONArray(key)));
            }
        });
        Registry<Enchantment> registry = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);

        readIngredients(ingredientMap);
        readEnchants   (enchantMap , registry);
        readCatalysts  (catalystMap, registry);
    }

    private static void readCatalysts(HashMap<String, JSONArray> catalystMap, Registry<Enchantment> registryEnchant) {
        AlchemicalInfusions.CATALYST_MAP = new HashMap<>();

        catalystMap.forEach((itemID, enchants)-> {
            LoreComponent lore = LoreComponent.DEFAULT;

            RegistryKey<Enchantment>[] array = new RegistryKey[enchants.length()];
            for (int i = 0; i < enchants.length(); i++) {
                java.util.Optional<net.minecraft.registry.RegistryKey<Enchantment>> test = registryEnchant.getKey(registryEnchant.get(Identifier.of(enchants.getString(i))));
                if (test.isPresent()) {
                    array[i] = test.get();
                    RegistryEntry<Enchantment> e = registryEnchant.getEntry(registryEnchant.get(array[i]));
                    lore = lore.with(Enchantment.getName(e, e.value().getMaxLevel()).copy().setStyle(Style.EMPTY.withColor(Formatting.GRAY).withItalic(false)));
                }
            }

            Item item = Registries.ITEM.get(Identifier.of(itemID));
            addComponents(item, DataComponentTypes.LORE, lore);
            AlchemicalInfusions.CATALYST_MAP.put(item, array);
        });
    }

    private static void readEnchants(HashMap<String, JSONArray> idMap, Registry<Enchantment> registryEnchant) {
        AlchemicalInfusions.INFUSION_MAP = new HashMap<>();
        idMap.forEach((effect, enchants) -> {
            Enchantment[] array = new Enchantment[enchants.length()];
            for (int i = 0; i < enchants.length(); i++) {
                array[i] = registryEnchant.get(Identifier.of(enchants.getString(i)));
            }
            AlchemicalInfusions.INFUSION_MAP.put(Registries.STATUS_EFFECT.getEntry(Registries.STATUS_EFFECT.get(Identifier.of(effect))), array);
        });
    }

    private static void readIngredients(HashMap<String, JSONObject> idMap) {
        //Add merged maps effects to items
        Registry<Item> registry =  Registries.ITEM;
        idMap.forEach((id, effects) -> {
            ArrayList<StatusEffectInstance> list = new ArrayList<>();
            effects.keySet().forEach(key -> Registries.STATUS_EFFECT.getEntry(Identifier.of(key)).ifPresent(statusEffectReference -> list.add(new StatusEffectInstance(statusEffectReference, effects.getInt(key)))));
            addEffects(registry.get(Identifier.of(id)), list.toArray(new StatusEffectInstance[0]));
        });
    }

    private static void addEffects(final Item ingredient, final StatusEffectInstance... effects) {
        PotionContentsComponent components = PotionContentsComponent.DEFAULT;
        for (final StatusEffectInstance effect : effects) {
            components = components.with(effect);
        }
        addComponents(ingredient, DataComponentTypes.POTION_CONTENTS, components);
    }

    public static <T> void addComponents(Item item, ComponentType<T> component, @Nullable T value) {
        ComponentMap.Builder builder = ComponentMap.builder().addAll(item.getComponents());
        builder.add(component, value);
        ((ComponentMixin)item).setComponents(builder.build());
    }
}
