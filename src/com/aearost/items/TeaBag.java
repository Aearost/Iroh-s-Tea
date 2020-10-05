package com.aearost.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.irohstea.Items;
import com.aearost.irohstea.Utils;

public abstract class TeaBag {
	
	public static ItemStack getTeaBag(Items teaItemName) {
		TeaItem teaItem = Utils.getTeaItem(teaItemName);
		String teaBagName = teaItem.getTeaBagName();
		
		ItemStack bag = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = bag.getItemMeta();
		
		meta.setDisplayName(Utils.translateToColor(teaBagName));
	    bag.setItemMeta(meta);
	    
	    return bag;
	}
	
}