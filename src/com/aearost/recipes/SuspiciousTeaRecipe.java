package com.aearost.recipes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapelessRecipe;

import org.bukkit.plugin.Plugin;

import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class SuspiciousTeaRecipe {

	public SuspiciousTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Suspicious Tea Bag.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "SUSPICIOUS_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.SUSPICIOUS_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		List<Material> tulips = new ArrayList<>();
		tulips.add(Material.ORANGE_TULIP);
		tulips.add(Material.PINK_TULIP);
		tulips.add(Material.RED_TULIP);
		tulips.add(Material.WHITE_TULIP);
		
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.ALLIUM);
		recipe.addIngredient(1, Material.BLUE_ORCHID);
		recipe.addIngredient(1, Material.CORNFLOWER);
		recipe.addIngredient(1, Material.DANDELION);
		recipe.addIngredient(1, Material.LILY_OF_THE_VALLEY);
		recipe.addIngredient(1, Material.OXEYE_DAISY);
		recipe.addIngredient(1, Material.POPPY);
		recipe.addIngredient(new MaterialChoice(tulips));
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
