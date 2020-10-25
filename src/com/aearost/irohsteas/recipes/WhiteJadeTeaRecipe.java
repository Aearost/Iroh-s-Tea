package com.aearost.irohsteas.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.irohsteas.items.Items;
import com.aearost.irohsteas.items.TeaBag;

public class WhiteJadeTeaRecipe {

	public WhiteJadeTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the White Jade Tea Bag
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "WHITE_JADE_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.WHITE_JADE_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.PAPER);
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.LILY_OF_THE_VALLEY);
		recipe.addIngredient(1, Material.WHITE_TULIP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
