package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class AppleCinnamonTeaRecipe {

	public AppleCinnamonTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Apple Cinnamon Tea Bag.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "APPLE_CINNAMON_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.APPLE_CINNAMON_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.APPLE);
		recipe.addIngredient(1, Material.WHEAT_SEEDS);
		recipe.addIngredient(1, Material.DARK_OAK_LOG);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
