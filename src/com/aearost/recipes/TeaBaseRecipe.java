package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.TeaBase;

public class TeaBaseRecipe {

	public TeaBaseRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Tea Base
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "TEA_BASE");
		
		ItemStack teaBase = TeaBase.getTeaBase();
		ShapedRecipe recipe = new ShapedRecipe(nk, teaBase);
		recipe.shape("XSX", "PTP", "XPX");
		recipe.setIngredient('X', Material.AIR);
		recipe.setIngredient('S', Material.STRING);
		recipe.setIngredient('P', Material.PAPER);
		recipe.setIngredient('T', Material.KELP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
