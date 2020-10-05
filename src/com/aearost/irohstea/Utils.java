package com.aearost.irohstea;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.aearost.items.CactusJuice;
import com.aearost.items.Tea;
import com.aearost.items.TeaBag;
import com.aearost.items.TeaItem;
import com.aearost.items.TeaLeaf;

public class Utils {

	public final static int MAXIMUM_ITEM_AMOUNT = 2034;
	private final static HashMap<Items, ItemStack> itemsToItemStack = new HashMap<Items, ItemStack>();
	public final static HashMap<Items, TeaItem> itemsToTea = new HashMap<Items, TeaItem>();

	public Utils() {
		initializeTeas();
		initializeItemsToItemStack();
	}

	private void initializeTeas() {
		TeaItem greenTea = new TeaItem("&2Green Tea", "&a&oSimple, yet delicious",
				new PotionEffect(PotionEffectType.LUCK, 60, 2), Color.fromRGB(90, 220, 90));
		itemsToTea.put(Items.GREEN_TEA, greenTea);
	}
	
	private void initializeItemsToItemStack() {
		itemsToItemStack.put(Items.TEA_LEAF, TeaLeaf.getTeaLeaf());
		
		itemsToItemStack.put(Items.GREEN_TEA_BAG, TeaBag.getTeaBag(Items.GREEN_TEA_BAG));
		itemsToItemStack.put(Items.JASMINE_TEA_BAG, TeaBag.getTeaBag(Items.JASMINE_TEA_BAG));
		
		itemsToItemStack.put(Items.GREEN_TEA, Tea.getTea(Items.GREEN_TEA));
		
		itemsToItemStack.put(Items.CACTUS_JUICE, CactusJuice.getCactusJuice());
	}
	
	public static TeaItem getTeaItem(Items tea) {
		Bukkit.getLogger().info("In getTeaItem");
		return itemsToTea.get(tea);
	}
	
	public static ItemStack getItem(String itemName) {
		Items i = Items.valueOf(itemName);
		return itemsToItemStack.get(i);
	}

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

	/**
	 * Adds the ItemStack to the player's inventory.
	 * 
	 * Returns 0 if the entire ItemStack was added. Returns -1 if none of the
	 * ItemStack fit. Returns a the remainder of what could not be fit, and adds
	 * what could.
	 * 
	 * @param player
	 * @param itemToAdd
	 * @return
	 */
	public static int addToInventory(Player player, ItemStack itemToAdd) {
		int amount = itemToAdd.getAmount();

		// Prioritizes filling up non-full stacks of the item in the player's inventory
		ItemStack[] inventory = player.getInventory().getStorageContents();
		for (ItemStack is : inventory) {
			if (is != null) {
				if (amount == 0) {
					return 0;
				}
				// Fill up an empty stack until it's full while removing one amount each
				// iteration
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
		return 0;
	}

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
