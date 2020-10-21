package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class TulipTeaRecipe {

	public TulipTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Tulip Tea Bag
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "TULIP_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.TULIP_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.ORANGE_TULIP);
		recipe.addIngredient(1, Material.PINK_TULIP);
		recipe.addIngredient(1, Material.RED_TULIP);
		recipe.addIngredient(1, Material.WHITE_TULIP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
