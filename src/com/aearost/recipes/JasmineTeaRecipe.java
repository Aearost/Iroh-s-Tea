package com.aearost.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.aearost.irohstea.Items;
import com.aearost.irohstea.Utils;
import com.aearost.items.TeaBag;

public class JasmineTeaRecipe {

	public JasmineTeaRecipe(Plugin plugin) {
		createRecipe(plugin);
	}
	
	private void createRecipe(Plugin plugin) {
		NamespacedKey nk = new NamespacedKey(plugin, "JASMINE_TEA");
		
		// Use below in cauldron brewing mechanic
//		PotionEffect pe = new PotionEffect(PotionEffectType.SPEED, 200, 1);
//		Color c = Color.fromRGB(220, 255, 115);
		
		ItemStack teaBag = TeaBag.getTeaBag(Items.JASMINE_TEA_BAG);
//		ItemStack teaBag = Utils.getItem(Items.JASMINE_TEA_BAG.name());
		ShapelessRecipe recipe = new ShapelessRecipe(nk, teaBag);
		
		recipe.addIngredient(1, Material.KELP);
		recipe.addIngredient(1, Material.WHITE_TULIP);
		recipe.addIngredient(1, Material.PEONY);
		
        plugin.getServer().addRecipe(recipe);
	}
	
}
