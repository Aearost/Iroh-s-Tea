package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.utils.ChatUtils;

public class TeaLeaf {
	
	public static ItemStack getTeaLeaf() {
		ItemStack tea = new ItemStack(Material.KELP, 1);
		ItemMeta meta = tea.getItemMeta();
		ArrayList<String> s = new ArrayList<>();
		
		meta.setDisplayName(ChatUtils.translateToColor(getName()));
	    s.add(ChatUtils.translateToColor(getLore()));
	    meta.setLore(s);
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
