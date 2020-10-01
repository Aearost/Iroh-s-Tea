package com.aearost.irohstea;

import java.util.ListIterator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {
	
	/**
	 * Allows the formatting of messages to contain Minecraft colors
	 * 
	 * @param msg
	 * @return
	 */
	public static String chatMessage(String msg) {
		return ChatColor.translateAlternateColorCodes('&', "&2[&aIroh's Teas&2] &r" + msg);
	}
	
	/**
	 * Allows the formatting of messages to contain Minecraft colors
	 * 
	 * @param msg
	 * @return
	 */
	public static String translateToColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static boolean hasInventorySpace(Player player)
	{
	    return player.getInventory().firstEmpty() != -1;
	}
	
	public static boolean isMatchingItemStack(ItemStack inventoryItem, ItemStack itemToAdd) {
		return (inventoryItem.getItemMeta().getLore().get(0).equals(itemToAdd.getItemMeta().getLore().get(0)) &&
				inventoryItem.getItemMeta().getDisplayName().equals(itemToAdd.getItemMeta().getDisplayName()));
	}
}
