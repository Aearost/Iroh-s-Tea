package com.aearost.irohstea;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {
	
	public final static int MAXIMUM_ITEM_AMOUNT = 2034;
	
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
	
	public static int hasInventorySpace(Player player, ItemStack itemToAdd)
	{
		int amount = itemToAdd.getAmount();
		
		// Prioritizes filling up non-full stacks of the item in the player's inventory
	    ItemStack[] inventory = player.getInventory().getStorageContents();
	    for (ItemStack is : inventory) {
	    	if (is != null) {
	    		if (amount == 0) {
	    			return 0;
	    		}
	    		// Fill up an empty stack until it's full while removing one amount each iteration
	    		if (is.getItemMeta().getDisplayName().equals(itemToAdd.getItemMeta().getDisplayName())) {
	    			while (amount > 0) {
	    				if (is.getAmount() < 64) {
	    					is.setAmount(is.getAmount() + 1);
	    					amount--;
	    				} else {
	    					break;
	    				}
	    			}
	    		}
	    	}
	    }
	    
	    // Prioritizes filling up empty inventory slots
	    ItemStack is = itemToAdd.clone();
	    while (amount > 0) {
	    	// When there is inventory space
	    	if (player.getInventory().firstEmpty() != -1) {
	    		player.sendMessage("Empty inventory slots");
	    		if (amount > 64) {
	    			is = itemToAdd.clone();
	    			is.setAmount(64);
	    			player.getInventory().addItem(is);
	    			amount = amount - 64;
	    		} else {
	    			is = itemToAdd.clone();
	    			is.setAmount(amount);
	    			player.getInventory().addItem(is);
	    			return 0;
	    		}
    		// Some was placed in the inventory, but not all
	    	} else if (amount < itemToAdd.getAmount()) {
	    		return amount;
    		// No space in the inventory
	    	} else {
	    		return -1;
	    	}
	    }
	    return -1;
	}
	
	public static boolean isMatchingItemStack(ItemStack inventoryItem, ItemStack itemToAdd) {
		return (inventoryItem.getItemMeta().getLore().get(0).equals(itemToAdd.getItemMeta().getLore().get(0)) &&
				inventoryItem.getItemMeta().getDisplayName().equals(itemToAdd.getItemMeta().getDisplayName()));
	}
}
