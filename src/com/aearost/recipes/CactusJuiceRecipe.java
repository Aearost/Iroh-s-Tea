package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.CactusJuice;

public class CactusJuiceRecipe {
	
	public CactusJuiceRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Cactus Juice.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "CACTUS_JUICE");
		ItemStack juice = CactusJuice.getCactusJuice();
		ShapelessRecipe recipe = new ShapelessRecipe(nk, juice);
		recipe.addIngredient(4, Material.CACTUS);
        plugin.getServer().addRecipe(recipe);
	}
	
}
