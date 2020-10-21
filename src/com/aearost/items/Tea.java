package com.aearost.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import com.aearost.utils.ChatUtils;
import com.aearost.utils.ItemUtils;

public class Tea {
	
	/**
	 * Returns an ItemStack of the input tea, representing a tea.
	 * 
	 * @param teaItemName
	 * @return
	 */
	public static ItemStack getTea(Items teaItemName) {
		ItemStack tea = new ItemStack(Material.POTION, 1);
		PotionMeta meta = (PotionMeta) tea.getItemMeta();
		TeaItem teaItem = ItemUtils.getTeaItem(teaItemName);
		
		meta.clearCustomEffects();
		ArrayList<String> s = new ArrayList<>();
 	    s.add(ChatUtils.translateToColor(teaItem.getLore()));
 	    meta.setLore(s);
 	    List<PotionEffect> potionEffects = teaItem.getPotionEffects();
 	    for (PotionEffect potionEffect : potionEffects) {
 	    	meta.addCustomEffect(potionEffect, true);
 	    }
		meta.setColor(teaItem.getColor());
		meta.setDisplayName(ChatUtils.translateToColor(teaItem.getName()));
		tea.setItemMeta(meta);
		return tea;
	}
	
}
