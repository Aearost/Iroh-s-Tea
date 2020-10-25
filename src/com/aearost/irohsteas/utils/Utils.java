package com.aearost.irohsteas.utils;

import org.bukkit.inventory.ItemStack;

public class Utils {

	public final static int MAXIMUM_ITEM_AMOUNT = 2034;
	public final static int DROP_RATE_MODIFIER = 2;

	/**
	 * Determines if the two items have the same meta values. Amount is not
	 * considered.
	 * 
	 * @param inventoryItem
	 * @param itemToAdd
	 * @return
	 */
	public static boolean isMatchingItemStack(ItemStack inventoryItem, ItemStack itemToAdd) {
		return (inventoryItem.getItemMeta().getLore().get(0).equals(itemToAdd.getItemMeta().getLore().get(0))
				&& inventoryItem.getItemMeta().getDisplayName().equals(itemToAdd.getItemMeta().getDisplayName()));
	}
}
