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
import com.aearost.utils.ChatUtils;
import com.aearost.utils.ItemUtils;
import com.aearost.utils.KettleUtils;

public class CauldronBrewing implements Listener {
	public CauldronBrewing(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCauldronClick(final PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			Block b = e.getClickedBlock();
			Location l = b.getLocation();
			if (b.getType() == Material.CAULDRON) {
				ItemStack is = p.getInventory().getItemInMainHand();
				Kettle ci = KettleUtils.getKettle(l);

				// If the cauldron already contains an item
				Levelled cauldron = (Levelled) b.getBlockData();
				if (cauldron.getLevel() > 0) {
					if (ci != null) {
						// If nothing is in their hand and something is in the cauldron
						if (is.getType() == Material.AIR) {
							if (ci.getHasBottle()) {
								l.getWorld().dropItemNaturally(l, new ItemStack(Material.GLASS_BOTTLE, 1));
								p.sendMessage(ChatUtils.chatMessage("&7You have retrieved the bottle by the kettle"));
							} else if (ci.getHasTeaBag()) {
								l.getWorld().dropItemNaturally(l,
										TeaBag.getTeaBag(Items.valueOf(ItemUtils.getTeaName(ci.getTea()) + "_TEA")));
								p.sendMessage(ChatUtils.chatMessage("&7You have taken the tea bag out of the kettle"));
							}
							KettleUtils.removeKettle(l);
							p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
							return;
						}

						// If it's a tea bag
						if (isTeaBag(is)) {
							if (isFire(b.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() - 1, l.getBlockZ()))) {
								if (!ci.getHasTeaBag()) {
									ci.setHasTeaBag(true);
									String teaName = ItemUtils.getTeaName(is);
									ItemStack tea = ItemUtils.getItem(teaName);
									ci.setTea(tea);
									KettleUtils.setKettle(l, ci);
									is.setAmount(is.getAmount() - 1);
									p.sendMessage(ChatUtils.chatMessage("&aYou have added a tea bag to the kettle!"));
								} else {
									p.sendMessage(
											ChatUtils.chatMessage("&cYou have already added a tea bag to the kettle!"));
								}
							} else {
								p.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						} else if (is.getType() == Material.GLASS_BOTTLE) {
							e.setCancelled(true);
							if (isFire(b.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() - 1, l.getBlockZ()))) {
								if (!ci.getHasBottle()) {
									ci.setHasBottle(true);
									KettleUtils.setKettle(l, ci);
									is.setAmount(is.getAmount() - 1);
									p.sendMessage(ChatUtils.chatMessage("&aYou have placed a bottle next to the kettle!"));
								} else {
									p.sendMessage(ChatUtils.chatMessage("&cYou have already placed a bottle there!"));
								}
							} else {
								p.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								return;
							}
						} else {
							if (is.getType() != Material.WATER_BUCKET) {
								p.sendMessage(ChatUtils.chatMessage("&cThat is not a valid ingredient!"));
								return;
							}
						}
					} else {
						if (isTeaBag(is)) {
							if (isFire(b.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() - 1, l.getBlockZ()))) {
								ci = new Kettle(false, true);
								ci.setHasTeaBag(true);
								String teaName = ItemUtils.getTeaName(is);
								ItemStack tea = ItemUtils.getItem(teaName);
								ci.setTea(tea);
								KettleUtils.setKettle(l, ci);
								is.setAmount(is.getAmount() - 1);
								p.sendMessage(ChatUtils.chatMessage("&aYou have added a tea bag to the kettle!"));
								p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
							} else {
								p.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						} else if (is.getType() == Material.GLASS_BOTTLE) {
							e.setCancelled(true);
							if (isFire(b.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() - 1, l.getBlockZ()))) {
								ci = new Kettle(true, false);
								KettleUtils.setKettle(l, ci);
								is.setAmount(is.getAmount() - 1);
								p.sendMessage(ChatUtils.chatMessage("&aYou have placed a bottle next to the kettle!"));
								p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
							} else {
								p.sendMessage(ChatUtils.chatMessage("&cThere is no flame beneath the kettle!"));
								e.setCancelled(true);
								return;
							}
						}
					}

					// If both ingredients have been put in the cauldron
					if (ci != null && ci.getHasBottle() && ci.getHasTeaBag()) {
						p.sendMessage(ChatUtils.chatMessage("&2Your tea has been brewed!"));

						// Brew the tea
						ItemStack tea = ci.getTea();
						l.getWorld().dropItemNaturally(l, tea);
						p.playSound(l, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
						int cauldronRemainder = cauldron.getLevel() - 1;
						cauldron.setLevel(cauldronRemainder);
						b.setBlockData(cauldron);
						KettleUtils.removeKettle(l);
					}
				} else {
					if (isTeaBag(is) || is.getType() == Material.GLASS_BOTTLE) {
						p.sendMessage(ChatUtils.chatMessage("&cThere is no water in the cauldron!"));
						e.setCancelled(true);
					}
				}
			}
		}
	}

	private boolean isTeaBag(ItemStack is) {
		return is.getType() == Material.PAPER && is.getItemMeta().hasDisplayName() && is.getItemMeta().hasLore();
	}

	private boolean isFire(Block b) {
		Material m = b.getType();
		return (m == Material.FIRE || m == Material.SOUL_FIRE || m == Material.CAMPFIRE || m == Material.SOUL_CAMPFIRE);
	}
}
