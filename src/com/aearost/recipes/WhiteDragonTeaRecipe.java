package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class WhiteDragonTeaRecipe {

	public WhiteDragonTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the White Dragon Tea Bag
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "WHITE_DRAGON_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.WHITE_DRAGON_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.AZURE_BLUET);
		recipe.addIngredient(1, Material.WHITE_TULIP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
