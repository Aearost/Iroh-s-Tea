package com.aearost.irohsteas.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.irohsteas.items.Items;
import com.aearost.irohsteas.items.TeaBag;

public class GreenTeaRecipe {

	public GreenTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Green Tea Bag.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "GREEN_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.GREEN_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.KELP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
