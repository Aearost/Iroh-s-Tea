package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.utils.ChatUtils;

public class TeaLeaf {
	
	/**
	 * Returns an ItemStack of a single Tea Leaf.
	 * @return
	 */
	public static ItemStack getTeaLeaf() {
		ItemStack tea = new ItemStack(Material.KELP, 1);
		ItemMeta meta = tea.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		
		meta.setDisplayName(ChatUtils.translateToColor(getName()));
	    lore.add(ChatUtils.translateToColor(getLore()));
	    meta.setLore(lore);
	    tea.setItemMeta(meta);
	    
	    return tea;
	}
	
	public static String getName() {
		return "&2&lTea Leaf";
	}
	
	public static String getLore() {
		return "&a&oThe base of it all";
	}
	
}
