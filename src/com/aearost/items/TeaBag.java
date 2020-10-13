package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.utils.ChatUtils;
import com.aearost.utils.ItemUtils;

public abstract class TeaBag {
	
	public static ItemStack getTeaBag(Items teaItemName) {
		TeaItem teaItem = ItemUtils.getTeaItem(teaItemName);
		String teaBagName = teaItem.getTeaBagName();
		
		ItemStack bag = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = bag.getItemMeta();
		
		meta.setDisplayName(ChatUtils.translateToColor(teaBagName));
		ArrayList<String> s = new ArrayList<>();
 	    s.add(ChatUtils.translateToColor(teaItem.getLore()));
 	    meta.setLore(s);
	    bag.setItemMeta(meta);
	    
	    return bag;
	}
	
}