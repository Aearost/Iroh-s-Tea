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
		meta.setDisplayName(Utils.chat("&2&lTea Leaf"));
		String lore = "&a&oThe base of it all";
	    s.add(Utils.chat(lore));
	    meta.setLore(s);
	    tea.setItemMeta(meta);
	    return tea;
	}
	
}
