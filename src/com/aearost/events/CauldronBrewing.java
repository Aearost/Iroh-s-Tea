package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.Main;
import com.aearost.irohstea.Utils;
import com.aearost.items.CauldronInfo;

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
				
				if (ci != null) {
					// If it's a tea bag
					if (is.getType() == Material.PAPER) {
						if (!ci.getHasTeaBag()) {
							ci.setHasTeaBag(true);
							String teaName = getTeaName(is);
							ItemStack tea = Utils.getItem(teaName);
							ci.setTea(tea);
							Utils.setCauldronInfo(l, ci);
							is.setAmount(is.getAmount() - 1);
							p.sendMessage(Utils.chatMessage("&aYou have added a tea bag to the kettle!"));
						} else {
							p.sendMessage(Utils.chatMessage("&cYou have already added a tea bag to the kettle!"));
						}
					} else if (is.getType() == Material.GLASS_BOTTLE) {
						if (!ci.getHasBottle()) {
							ci.setHasBottle(true);
							Utils.setCauldronInfo(l, ci);
							is.setAmount(is.getAmount() - 1);
							p.sendMessage(Utils.chatMessage("&aYou have placed a bottle next to the kettle!"));
						} else {
							p.sendMessage(Utils.chatMessage("&cYou have already placed a bottle there!"));
						}
					// Not a valid ingredient
					} else {
						return;
					}
				} else {
					if (is.getType() == Material.PAPER) {
						ci = new CauldronInfo(false, true);
						ci.setHasTeaBag(true);
						String teaName = getTeaName(is);
						ItemStack tea = Utils.getItem(teaName);
						ci.setTea(tea);
						Utils.setCauldronInfo(l, ci);
						is.setAmount(is.getAmount() - 1);
						p.sendMessage(Utils.chatMessage("&aYou have added a tea bag to the kettle!"));
					} else if (is.getType() == Material.GLASS_BOTTLE) {
						ci = new CauldronInfo(true, false);
						Utils.setCauldronInfo(l, ci);
						is.setAmount(is.getAmount() - 1);
						p.sendMessage(Utils.chatMessage("&aYou have placed a bottle next to the kettle!"));
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
					Utils.removeCauldronInfo(l);
				}
				
			}
		}
	}
	
	private String getTeaName(ItemStack is) {
		String teaName = is.getItemMeta().getDisplayName();
		teaName = teaName.substring(2, teaName.length() - 4);
		teaName = teaName.toUpperCase();
		teaName = teaName.replace(" ", "_");
		return teaName;
	}
}
