package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.irohstea.Items;
import com.aearost.items.TeaBag;

public class JasmineTeaRecipe {

	public JasmineTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "JASMINE_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.JASMINE_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.WHITE_TULIP);
		recipe.addIngredient(1, Material.PEONY);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
