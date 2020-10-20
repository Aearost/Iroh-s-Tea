package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.Main;
import com.aearost.utils.ChatUtils;

public class TeaCrafting implements Listener {

	public TeaCrafting(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraftTea(final CraftItemEvent e) {
		HumanEntity player = e.getWhoClicked();
		for (ItemStack is : e.getInventory().getContents()) {
			if (is.getType() == Material.KELP && e.getRecipe().getResult().getType() == Material.PAPER) {
				if (!is.getItemMeta().hasLore()) {
					player.sendMessage(ChatUtils.chatMessage("&cYou must use a tea leaf, not kelp!"));
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}
