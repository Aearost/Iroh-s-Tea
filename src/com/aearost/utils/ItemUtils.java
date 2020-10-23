package com.aearost.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.aearost.items.CactusJuice;
import com.aearost.items.Items;
import com.aearost.items.Tea;
import com.aearost.items.TeaBag;
import com.aearost.items.TeaBase;
import com.aearost.items.TeaItem;
import com.aearost.items.TeaLeaf;
import com.aearost.items.TeaPlant;

public class ItemUtils {

	private final static HashMap<String, ItemStack> itemsToItemStack = new HashMap<String, ItemStack>();
	public final static HashMap<String, TeaItem> itemsToTea = new HashMap<String, TeaItem>();

	public ItemUtils() {
		initializeTeas();
		initializeItemsToItemStack();
	}

	/**
	 * Instantiates all teas and their TeaItem values to the itemsToTea HashMap.
	 */
	private void initializeTeas() {
		// Teas with a single effect
		itemsToTea.put(Items.GREEN_TEA.name(), new TeaItem("&2Green Tea", "&a&oSimple, yet delicious",
				new PotionEffect(PotionEffectType.LUCK, 600, 2), Color.fromRGB(90, 220, 90)));
		itemsToTea.put(Items.APPLE_CINNAMON_TEA.name(),
				new TeaItem("&cApple Cinnamon Tea", "&6&oJust a hint of tree bark",
						new PotionEffect(PotionEffectType.FAST_DIGGING, 1800, 1), Color.fromRGB(255, 153, 102)));
		itemsToTea.put(Items.CHOCOLATE_TEA.name(), new TeaItem("&6Chocolate Tea", "&7&oSweet goodness!",
				new PotionEffect(PotionEffectType.SPEED, 1200, 3), Color.fromRGB(123, 63, 0)));
		itemsToTea.put(Items.JASMINE_TEA.name(), new TeaItem("&eJasmine Tea", "&7&oThe sweet taste of blossoms",
				new PotionEffect(PotionEffectType.INVISIBILITY, 300, 0), Color.fromRGB(220, 255, 115)));
		itemsToTea.put(Items.MATCHA_TEA.name(), new TeaItem("&aMatcha Tea", "&7&oThe Japanese flavor you needed",
				new PotionEffect(PotionEffectType.JUMP, 1200, 2), Color.fromRGB(105, 191, 100)));
		itemsToTea.put(Items.T8_TEA.name(), new TeaItem("&4T8 Tea", "&c&oEver hear of V8?",
				new PotionEffect(PotionEffectType.REGENERATION, 600, 1), Color.fromRGB(217, 94, 41)));
		itemsToTea.put(Items.WHITE_DRAGON_TEA.name(), new TeaItem("&fWhite Dragon Tea", "&7&oDelicious tea?",
				new PotionEffect(PotionEffectType.HEAL, -1, 2), Color.fromRGB(230, 255, 240)));
		itemsToTea.put(Items.WHITE_JADE_TEA.name(), new TeaItem("&fWhite Jade Tea", "&7&oDeadly poison?",
				new PotionEffect(PotionEffectType.SLOW, 1800, 2), Color.fromRGB(240, 255, 230)));

		// Teas with multiple effects
		List<PotionEffect> chamomileEffects = new ArrayList<>();
		chamomileEffects.add(new PotionEffect(PotionEffectType.HEAL, -1, 4));
		chamomileEffects.add(new PotionEffect(PotionEffectType.SLOW, 1200, 1));
		chamomileEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, 1200, 1));
		itemsToTea.put(Items.CHAMOMILE_TEA.name(), new TeaItem("&eChamomile Tea", "&7Grandma, is that you?",
				chamomileEffects, Color.fromRGB(213, 182, 83)));
		List<PotionEffect> honeyRoseEffects = new ArrayList<>();
		honeyRoseEffects.add(new PotionEffect(PotionEffectType.LEVITATION, 100, 2));
		honeyRoseEffects.add(new PotionEffect(PotionEffectType.ABSORPTION, 160, 2));
		itemsToTea.put(Items.HONEY_ROSE_TEA.name(), new TeaItem("&4Honey Rose Tea",
				"&6Some say its taste is heavenly...", honeyRoseEffects, Color.fromRGB(150, 0, 24)));
		List<PotionEffect> suspiciousEffects = new ArrayList<>();
		suspiciousEffects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 80, 0));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, 160, 0));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.SATURATION, 20, 6));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.JUMP, 120, 3));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.POISON, 240, 0));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.REGENERATION, 160, 0));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.NIGHT_VISION, 100, 0));
		suspiciousEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, 180, 1));
		itemsToTea.put(Items.SUSPICIOUS_TEA.name(), new TeaItem("&8Suspicious Tea", "&7I wonder what this does...",
				suspiciousEffects, Color.fromRGB(107, 68, 35)));
		List<PotionEffect> tulipEffects = new ArrayList<>();
		tulipEffects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
		tulipEffects.add(new PotionEffect(PotionEffectType.ABSORPTION, 600, 2));
		itemsToTea.put(Items.TULIP_TEA.name(),
				new TeaItem("&dTulip Tea", "&cGot tulips?", tulipEffects, Color.fromRGB(255, 8, 0)));
	}

	/**
	 * Initializes all items into ItemStacks to the itemsToItemStack HashMap.
	 */
	private void initializeItemsToItemStack() {
		// Basic Items
		itemsToItemStack.put(Items.TEA_LEAF.name(), TeaLeaf.getTeaLeaf());
		itemsToItemStack.put(Items.TEA_PLANT.name(), TeaPlant.getTeaPlant());
		itemsToItemStack.put(Items.TEA_BASE.name(), TeaBase.getTeaBase());

		// Tea Bags
		itemsToItemStack.put(Items.APPLE_CINNAMON_TEA_BAG.name(), TeaBag.getTeaBag(Items.APPLE_CINNAMON_TEA));
		itemsToItemStack.put(Items.CHAMOMILE_TEA_BAG.name(), TeaBag.getTeaBag(Items.CHAMOMILE_TEA));
		itemsToItemStack.put(Items.CHOCOLATE_TEA_BAG.name(), TeaBag.getTeaBag(Items.CHOCOLATE_TEA));
		itemsToItemStack.put(Items.GREEN_TEA_BAG.name(), TeaBag.getTeaBag(Items.GREEN_TEA));
		itemsToItemStack.put(Items.HONEY_ROSE_TEA_BAG.name(), TeaBag.getTeaBag(Items.HONEY_ROSE_TEA));
		itemsToItemStack.put(Items.JASMINE_TEA_BAG.name(), TeaBag.getTeaBag(Items.JASMINE_TEA));
		itemsToItemStack.put(Items.MATCHA_TEA_BAG.name(), TeaBag.getTeaBag(Items.MATCHA_TEA));
		itemsToItemStack.put(Items.SUSPICIOUS_TEA_BAG.name(), TeaBag.getTeaBag(Items.SUSPICIOUS_TEA));
		itemsToItemStack.put(Items.T8_TEA_BAG.name(), TeaBag.getTeaBag(Items.T8_TEA));
		itemsToItemStack.put(Items.TULIP_TEA_BAG.name(), TeaBag.getTeaBag(Items.TULIP_TEA));
		itemsToItemStack.put(Items.WHITE_DRAGON_TEA_BAG.name(), TeaBag.getTeaBag(Items.WHITE_DRAGON_TEA));
		itemsToItemStack.put(Items.WHITE_JADE_TEA_BAG.name(), TeaBag.getTeaBag(Items.WHITE_JADE_TEA));

		// Teas
		itemsToItemStack.put(Items.APPLE_CINNAMON_TEA.name(), Tea.getTea(Items.APPLE_CINNAMON_TEA));
		itemsToItemStack.put(Items.CHAMOMILE_TEA.name(), Tea.getTea(Items.CHAMOMILE_TEA));
		itemsToItemStack.put(Items.CHOCOLATE_TEA.name(), Tea.getTea(Items.CHOCOLATE_TEA));
		itemsToItemStack.put(Items.GREEN_TEA.name(), Tea.getTea(Items.GREEN_TEA));
		itemsToItemStack.put(Items.HONEY_ROSE_TEA.name(), Tea.getTea(Items.HONEY_ROSE_TEA));
		itemsToItemStack.put(Items.JASMINE_TEA.name(), Tea.getTea(Items.JASMINE_TEA));
		itemsToItemStack.put(Items.MATCHA_TEA.name(), Tea.getTea(Items.MATCHA_TEA));
		itemsToItemStack.put(Items.SUSPICIOUS_TEA.name(), Tea.getTea(Items.SUSPICIOUS_TEA));
		itemsToItemStack.put(Items.T8_TEA.name(), Tea.getTea(Items.T8_TEA));
		itemsToItemStack.put(Items.TULIP_TEA.name(), Tea.getTea(Items.TULIP_TEA));
		itemsToItemStack.put(Items.WHITE_DRAGON_TEA.name(), Tea.getTea(Items.WHITE_DRAGON_TEA));
		itemsToItemStack.put(Items.WHITE_JADE_TEA.name(), Tea.getTea(Items.WHITE_JADE_TEA));

		// Other Drinks
		itemsToItemStack.put(Items.CACTUS_JUICE.name(), CactusJuice.getCactusJuice());
	}

	public static TeaItem getTeaItem(Items tea) {
		return itemsToTea.get(tea.name());
	}

	public static ItemStack getItem(String itemName) {
		Items i = Items.valueOf(itemName.toUpperCase());
		return itemsToItemStack.get(i.name());
	}

	public static String getTeaName(ItemStack is) {
		String teaName = is.getItemMeta().getDisplayName();
		teaName = teaName.substring(2, teaName.length() - 4);
		teaName = teaName.toUpperCase();
		teaName = teaName.replace(" ", "_");
		return teaName;
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

}
