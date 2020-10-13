package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import com.aearost.utils.ChatUtils;
import com.aearost.utils.ItemUtils;

public class Tea {
	
	public static ItemStack getTea(Items teaItemName) {
		ItemStack tea = new ItemStack(Material.POTION, 1);
		PotionMeta meta = (PotionMeta) tea.getItemMeta();
		TeaItem teaItem = ItemUtils.getTeaItem(teaItemName);
		
		meta.clearCustomEffects();
		ArrayList<String> s = new ArrayList<>();
 	    s.add(ChatUtils.translateToColor(teaItem.getLore()));
 	    meta.setLore(s);
		meta.addCustomEffect(teaItem.getPotionEffect(), true);
		meta.setColor(teaItem.getColor());
		meta.setDisplayName(ChatUtils.translateToColor(teaItem.getName()));
		tea.setItemMeta(meta);
		return tea;
	}
	
}
