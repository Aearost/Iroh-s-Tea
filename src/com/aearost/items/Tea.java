package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import com.aearost.irohstea.Utils;

public class Tea {
	
	public static ItemStack getTea(PotionEffect pe, Color c, String name, String lore) {
		ItemStack tea = new ItemStack(Material.POTION, 1);
		PotionMeta meta = (PotionMeta) tea.getItemMeta();
		
		meta.clearCustomEffects();
		meta.addCustomEffect(pe, true);
		meta.setColor(c);
		meta.setDisplayName(Utils.translateToColor(name));
		if (meta.hasLore()) {
			meta.getLore().clear();
		}
		ArrayList<String> s = new ArrayList<>();
 	    s.add(Utils.translateToColor(lore));
 	    meta.setLore(s);
		tea.setItemMeta(meta);
		return tea;
	}
	
}
