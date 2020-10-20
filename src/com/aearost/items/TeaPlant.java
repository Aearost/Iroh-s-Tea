package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aearost.utils.ChatUtils;

public class TeaPlant {

	/**
	 * Returns an ItemStack of a single Tea Plant.
	 * 
	 * @return
	 */
	public static ItemStack getTeaPlant() {
		ItemStack teaPlant = new ItemStack(Material.FERN, 1);
		ItemMeta meta = teaPlant.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();

		meta.setDisplayName(ChatUtils.translateToColor(getName()));
		lore.add(ChatUtils.translateToColor(getLore()));
		meta.setLore(lore);
		teaPlant.setItemMeta(meta);

		return teaPlant;
	}

	public static String getName() {
		return "&2&lTea Plant";
	}

	public static String getLore() {
		return "&7&oPlant this and watch it grow!";
	}

}
