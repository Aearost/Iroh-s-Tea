package com.aearost.irohsteas.recipes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.irohsteas.items.Items;
import com.aearost.irohsteas.items.TeaBag;

public class MatchaTeaRecipe {

	public MatchaTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	/**
	 * Creates the recipe for the Matcha Tea Bag.
	 * 
	 * Also allows for any type of leaves to be used as an ingredient.
	 * 
	 * @param plugin
	 */
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "MATCHA_TEA");
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.MATCHA_TEA);
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		List<Material> leaves = new ArrayList<>();
		leaves.add(Material.ACACIA_LEAVES);
		leaves.add(Material.BIRCH_LEAVES);
		leaves.add(Material.DARK_OAK_LEAVES);
		leaves.add(Material.JUNGLE_LEAVES);
		leaves.add(Material.OAK_LEAVES);
		leaves.add(Material.SPRUCE_LEAVES);
		
		recipe.addIngredient(3, Material.PAPER);
		recipe.addIngredient(1, Material.SUGAR);
		recipe.addIngredient(new MaterialChoice(leaves));
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
