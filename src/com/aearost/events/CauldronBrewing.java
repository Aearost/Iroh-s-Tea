package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.aearost.Main;
import com.aearost.items.Items;
import com.aearost.items.Kettle;
import com.aearost.items.TeaBag;
import com.aearost.items.TeaBase;
import com.aearost.utils.ChatUtils;
import com.aearost.utils.ItemUtils;
import com.aearost.utils.KettleUtils;

public class CauldronBrewing implements Listener {

	public CauldronBrewing(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Handles the brewing of teas.
	 * 
	 * All logic is here.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onCauldronClick(final PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = e.getPlayer();
			Block block = e.getClickedBlock();
			Location location = block.getLocation();
			if (block.getType() == Material.CAULDRON) {
				ItemStack is = player.getInventory().getItemInMainHand();
				Kettle kettle = KettleUtils.getKettle(location);

				// If the cauldron already contains an item
				Levelled cauldron = (Levelled) block.getBlockData();
				if (cauldron.getLevel() > 0) {
					if (kettle != null) {
						// If nothing is in their hand and something is in the cauldron
						if (is.getType() == Material.AIR) {
							if (kettle.getHasBottle()) {
								location.getWorld().dropItemNaturally(location,
										new ItemStack(Material.GLASS_BOTTLE, 1));
								player.sendMessage(
										ChatUtils.chatMessage("&7You have retrieved the bottle by the kettle"));
							} else if (kettle.getHasTeaBag()) {
								location.getWorld().dropItemNaturally(location, TeaBag
										.getTeaBag(Items.valueOf(ItemUtils.getTeaName(kettle.getTea()) + "_TEA")));
								player.sendMessage(
										ChatUtils.chatMessage("&7You have taken the tea bag out of the kettle"));
							}
							KettleUtils.removeKettle(location);
							player.playSound(location, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
							return;
						}

						// If it's a tea bag
						if (isTeaBag(is)) {
							if (isFire(block.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1,
									location.getBlockZ()))) {
								if (!kettle.getHasTeaBag()) {
									kettle.setHasTeaBag(true);
									String teaName = ItemUtils.getTeaName(is);
									ItemStack tea = ItemUtils.getItem(teaName);
									kettle.setTea(tea);
									KettleUtils.addKettle(location, kettle);
									is.setAmount(is.getAmount() - 1);
									player.sendMessage(
											ChatUtils.chatMessage("&aYou have added a tea bag to the kettle!"));
								} else {
									player.sendMessage(
											ChatUtils.chatMessage("&cYou have already added a tea bag to the kettle!"));
								}
							} else {
								player.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						} else if (is.getType() == Material.GLASS_BOTTLE) {
							e.setCancelled(true);
							if (isFire(block.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1,
									location.getBlockZ()))) {
								if (!kettle.getHasBottle()) {
									kettle.setHasBottle(true);
									KettleUtils.addKettle(location, kettle);
									is.setAmount(is.getAmount() - 1);
									player.sendMessage(
											ChatUtils.chatMessage("&aYou have placed a bottle next to the kettle!"));
								} else {
									player.sendMessage(
											ChatUtils.chatMessage("&cYou have already placed a bottle there!"));
								}
							} else {
								player.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								return;
							}
						} else {
							if (is.getType() != Material.WATER_BUCKET) {
								player.sendMessage(ChatUtils.chatMessage("&cThat is not a valid ingredient!"));
								return;
							}
						}
					} else {
						if (isTeaBag(is)) {
							if (isFire(block.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1,
									location.getBlockZ()))) {
								kettle = new Kettle(false, true);
								kettle.setHasTeaBag(true);
								String teaName = ItemUtils.getTeaName(is);
								ItemStack tea = ItemUtils.getItem(teaName);
								kettle.setTea(tea);
								KettleUtils.addKettle(location, kettle);
								is.setAmount(is.getAmount() - 1);
								player.sendMessage(ChatUtils.chatMessage("&aYou have added a tea bag to the kettle!"));
								player.playSound(location, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
							} else {
								player.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						} else if (is.getType() == Material.GLASS_BOTTLE) {
							e.setCancelled(true);
							if (isFire(block.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1,
									location.getBlockZ()))) {
								kettle = new Kettle(true, false);
								KettleUtils.addKettle(location, kettle);
								is.setAmount(is.getAmount() - 1);
								player.sendMessage(
										ChatUtils.chatMessage("&aYou have placed a bottle next to the kettle!"));
								player.playSound(location, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
							} else {
								player.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						}
					}

					// If both ingredients have been put in the cauldron
					if (kettle != null && kettle.getHasBottle() && kettle.getHasTeaBag()) {
						player.sendMessage(ChatUtils.chatMessage("&2Your tea has been brewed!"));

						// Brew the tea
						ItemStack tea = kettle.getTea();
						location.getWorld().dropItemNaturally(location, tea);
						player.playSound(location, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
						int cauldronRemainder = cauldron.getLevel() - 1;
						cauldron.setLevel(cauldronRemainder);
						block.setBlockData(cauldron);
						KettleUtils.removeKettle(location);
					}
				} else {
					if (isTeaBag(is) || is.getType() == Material.GLASS_BOTTLE) {
						player.sendMessage(ChatUtils.chatMessage("&cThere is no water in the cauldron!"));
						e.setCancelled(true);
					}
				}
			}
		}
	}

	/**
	 * Determines whether or not the input ItemStack is a valid tea bag item or not.
	 * 
	 * @param is
	 * @return
	 */
	private boolean isTeaBag(ItemStack is) {
		boolean isPaper = is.getType() == Material.PAPER;
		if (!is.hasItemMeta()) {
			return false;
		}
		boolean hasLore = is.getItemMeta().hasLore();
		if (!hasLore) {
			return false;
		}
		boolean isNotTeaBase = !ChatUtils.stripColor(is.getItemMeta().getLore().get(0)).equals(ChatUtils.stripColor(TeaBase.getLore()));
		return isPaper && isNotTeaBase;
	}

	/**
	 * Determines whether or not the input Block is a fire source or not.
	 * 
	 * @param b
	 * @return
	 */
	private boolean isFire(Block b) {
		Material m = b.getType();
		return (m == Material.FIRE || m == Material.SOUL_FIRE || m == Material.CAMPFIRE || m == Material.SOUL_CAMPFIRE);
	}
}