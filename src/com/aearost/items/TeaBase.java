package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.utils.ChatUtils;

public class TeaBase {

	/**
	 * Returns an ItemStack of a single Tea Base.
	 * 
	 * @return
	 */
	public static ItemStack getTeaBase() {
		ItemStack tea = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = tea.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		
		meta.setDisplayName(ChatUtils.translateToColor(getName()));
	    lore.add(ChatUtils.translateToColor(getLore()));
	    meta.setLore(lore);
	    tea.setItemMeta(meta);
	    
	    return tea;
	}
	
	public static String getName() {
		return "&2&lTea Base";
	}
	
	public static String getLore() {
		return "&7&oNo tea without this!";
	}
	
}
