package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class ChocolateTeaRecipe {

	public ChocolateTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Chocolate Tea Bag.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "CHOCOLATE_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.CHOCOLATE_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.MILK_BUCKET);
		recipe.addIngredient(1, Material.COCOA_BEANS);
		recipe.addIngredient(1, Material.SUGAR);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
