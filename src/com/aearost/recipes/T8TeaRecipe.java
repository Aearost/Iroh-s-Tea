package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class T8TeaRecipe {

	public T8TeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the T8 Tea Bag
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "T8_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.T8_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.CARROT);
		recipe.addIngredient(1, Material.CARROT);
		recipe.addIngredient(1, Material.BEETROOT);
		recipe.addIngredient(1, Material.BEETROOT);
		recipe.addIngredient(1, Material.MELON_SLICE);
		recipe.addIngredient(1, Material.SWEET_BERRIES);
		recipe.addIngredient(1, Material.SWEET_BERRIES);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
