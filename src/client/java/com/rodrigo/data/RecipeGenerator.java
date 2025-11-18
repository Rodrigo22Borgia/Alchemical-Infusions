package com.rodrigo.data;

import com.rodrigo.AlchemicalInfusions;
import com.rodrigo.blocks._BlockRegistry;
import com.rodrigo.items._ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.VanillaRecipeGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipe.RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        //recipeExporter.accept(RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(AlchemicalInfusions.modid, "bottle")), new ShapelessRecipe(null, CraftingRecipeCategory.MISC, Items.GLASS_BOTTLE.getDefaultStack(), List.of(Ingredient.ofItem(Items.GLASS_BOTTLE))), null);
          return   new net.minecraft.data.recipe.RecipeGenerator(wrapperLookup, recipeExporter) {
              @Override
              public void generate() {
                  offerShapelessRecipe(Items.GLASS_BOTTLE            , _ItemRegistry.lingering_bottle   , null, 1);
                  offerShapelessRecipe(_ItemRegistry.lingering_bottle   , _ItemRegistry.splash_bottle, null, 1);
                  offerShapelessRecipe(_ItemRegistry.splash_bottle, Items.GLASS_BOTTLE            , null, 1);

                  createShaped(RecipeCategory.BREWING, _BlockRegistry.alchemy).criterion(hasItem(Items.IRON_INGOT), this.conditionsFromItem(Items.IRON_INGOT))
                          .pattern("UIU")
                          .pattern("###")
                          .input('U', Items.GLASS_BOTTLE).input('I', Items.IRON_INGOT).input('#', Items.SMOOTH_STONE_SLAB).offerTo(recipeExporter);
              }
          };
    }



    @Override
    public String getName() {
        return "";
    }
}
