package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.items.GreenTeaBag;

public class GreenTeaRecipe {

	public GreenTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "GREEN_TEA");
		
		// Use below in cauldron brewing mechanic
//		PotionEffect pe = new PotionEffect(PotionEffectType.SPEED, 200, 1);
//		Color c = Color.fromRGB(220, 255, 115);
//		ItemStack tea = GreenTea.getTea(pe, c, GreenTea.getName(), GreenTea.getLore());
		
		ItemStack teaBag = GreenTeaBag.getTeaBag(GreenTeaBag.getName());
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		// Simulating the tea leaf, will catch in onCraftTeaBag
		recipe.addIngredient(1, Material.KELP);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
