package com.aearost.irohstea;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeaLeaf {
	
	public static ItemStack getTeaLeaf() {
		ItemStack tea = new ItemStack(Material.KELP, 1);
		ItemMeta meta = tea.getItemMeta();
		ArrayList<String> s = new ArrayList<>();
		
		meta.setDisplayName(Utils.translateToColor(getName()));
	    s.add(Utils.translateToColor(getLore()));
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
