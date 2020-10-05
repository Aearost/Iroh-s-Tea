package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.Main;
import com.aearost.irohstea.Utils;

public class TeaCrafting implements Listener {

	public TeaCrafting(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraftTea(final CraftItemEvent e) {
		for (ItemStack is : e.getInventory().getContents()) {
			if (is.getType() == Material.KELP && e.getRecipe().getResult().getType() == Material.PAPER) {
				if (!is.getItemMeta().hasLore()) {
					e.getWhoClicked().sendMessage(Utils.chatMessage("&cYou must use a tea leaf, not kelp!"));
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}
