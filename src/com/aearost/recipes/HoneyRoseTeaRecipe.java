package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class HoneyRoseTeaRecipe {

	public HoneyRoseTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Honey Rose Tea Bag.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "HONEY_ROSE_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.HONEY_ROSE_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.ROSE_BUSH);
		recipe.addIngredient(1, Material.HONEY_BOTTLE);
		recipe.addIngredient(1, Material.POPPY);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
