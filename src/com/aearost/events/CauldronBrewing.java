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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.Items;
import com.aearost.irohstea.Main;
import com.aearost.irohstea.Utils;
import com.aearost.items.CauldronInfo;
import com.aearost.items.TeaBag;

public class CauldronBrewing implements Listener {
	public CauldronBrewing(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCauldronClick(final PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND) {
			Player p = e.getPlayer();
			Block b = e.getClickedBlock();
			if (b.getType() == Material.CAULDRON) {
				ItemStack is = p.getInventory().getItemInMainHand();
				Location l = b.getLocation();
				CauldronInfo ci = Utils.getCauldronInfo(l);

				// If nothing is in their hand and something is in the cauldron
				if (is.getType() == Material.AIR && ci != null) {
					if (ci.getHasBottle()) {
						l.getWorld().dropItemNaturally(l, new ItemStack(Material.GLASS_BOTTLE, 1));
						p.sendMessage(Utils.chatMessage("&7You have retrieved the bottle by the kettle"));
					} else if (ci.getHasTeaBag()) {
						l.getWorld().dropItemNaturally(l,
								TeaBag.getTeaBag(Items.valueOf(Utils.getTeaName(ci.getTea()) + "_TEA")));
						p.sendMessage(Utils.chatMessage("&7You have taken the tea bag out of the kettle"));
					}
					Utils.removeCauldronInfo(l);
					p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					return;
				}

				// If the cauldron already contains an item
				if (ci != null) {
					// If it's a tea bag
					if (is.getType() == Material.PAPER && is.getItemMeta().hasDisplayName()
							&& is.getItemMeta().hasLore()) {
						Levelled cauldron = (Levelled) b.getBlockData();
						if (cauldron.getLevel() > 0) {
							if (!ci.getHasTeaBag()) {
								ci.setHasTeaBag(true);
								String teaName = Utils.getTeaName(is);
								ItemStack tea = Utils.getItem(teaName);
								ci.setTea(tea);
								Utils.setCauldronInfo(l, ci);
								is.setAmount(is.getAmount() - 1);
								p.sendMessage(Utils.chatMessage("&aYou have added a tea bag to the kettle!"));
							} else {
								p.sendMessage(Utils.chatMessage("&cYou have already added a tea bag to the kettle!"));
							}
						} else {
							p.sendMessage(Utils.chatMessage("&cThere is no water in the cauldron!"));
							e.setCancelled(true);
							return;
						}
					} else if (is.getType() == Material.GLASS_BOTTLE) {
						e.setCancelled(true);
						Levelled cauldron = (Levelled) b.getBlockData();
						if (cauldron.getLevel() > 0) {
							if (!ci.getHasBottle()) {
								ci.setHasBottle(true);
								Utils.setCauldronInfo(l, ci);
								is.setAmount(is.getAmount() - 1);
								p.sendMessage(Utils.chatMessage("&aYou have placed a bottle next to the kettle!"));
							} else {
								p.sendMessage(Utils.chatMessage("&cYou have already placed a bottle there!"));
							}
						} else {
							p.sendMessage(Utils.chatMessage("&cThere is no water in the cauldron!"));
							e.setCancelled(true);
							return;
						}

					// Not a valid ingredient
					} else {
						return;
					}
				} else {
					if (is.getType() == Material.PAPER && is.getItemMeta().hasDisplayName()
							&& is.getItemMeta().hasLore()) {
						Levelled cauldron = (Levelled) b.getBlockData();
						if (cauldron.getLevel() > 0) {
							ci = new CauldronInfo(false, true);
							ci.setHasTeaBag(true);
							String teaName = Utils.getTeaName(is);
							ItemStack tea = Utils.getItem(teaName);
							ci.setTea(tea);
							Utils.setCauldronInfo(l, ci);
							is.setAmount(is.getAmount() - 1);
							p.sendMessage(Utils.chatMessage("&aYou have added a tea bag to the kettle!"));
							p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
						} else {
							p.sendMessage(Utils.chatMessage("&cThere is no water in the cauldron!"));
							e.setCancelled(true);
							return;
						}
					} else if (is.getType() == Material.GLASS_BOTTLE) {
						e.setCancelled(true);
						Levelled cauldron = (Levelled) b.getBlockData();
						if (cauldron.getLevel() > 0) {
							ci = new CauldronInfo(true, false);
							Utils.setCauldronInfo(l, ci);
							is.setAmount(is.getAmount() - 1);
							p.sendMessage(Utils.chatMessage("&aYou have placed a bottle next to the kettle!"));
							p.playSound(l, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.5F);
						} else {
							p.sendMessage(Utils.chatMessage("&cThere is no water in the cauldron!"));
							e.setCancelled(true);
							return;
						}
					// Not a valid ingredient
					} else {
						return;
					}
				}

				// If both ingredients have been put in the cauldron
				if (ci.getHasBottle() && ci.getHasTeaBag()) {
					p.sendMessage(Utils.chatMessage("&2Your tea has been brewed!"));

					// Brew the tea
					ItemStack tea = ci.getTea();
					l.getWorld().dropItemNaturally(l, tea);
					p.playSound(l, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
					Levelled cauldron = (Levelled) b.getBlockData();
					int cauldronRemainder = cauldron.getLevel() - 1;
					cauldron.setLevel(cauldronRemainder);
					b.setBlockData(cauldron);
					Utils.removeCauldronInfo(l);
				}

			}
		}
	}
}
