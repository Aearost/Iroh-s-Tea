package com.aearost.irohsteas.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohsteas.Main;
import com.aearost.irohsteas.items.TeaBase;
import com.aearost.irohsteas.utils.ChatUtils;

public class TeaCrafting implements Listener {

	public TeaCrafting(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Handles the crafting of a tea.
	 * 
	 * If the input ingredients are not a tea leaf nor a tea bag, the result will
	 * not be crafted successfully and an error message will be displayed.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onCraftTea(final CraftItemEvent e) {
		HumanEntity player = e.getWhoClicked();
		for (ItemStack is : e.getInventory().getMatrix()) {
			
			if (is == null) {
				continue;
			}
			// Crafting tea base with kelp
			else if (is.getType() == Material.KELP && e.getRecipe().getResult().getType() == Material.PAPER) {
				if (!is.getItemMeta().hasLore()) {
					player.sendMessage(ChatUtils.chatMessage("&cYou must use a tea leaf, not kelp!"));
					e.setCancelled(true);
					return;
				}
			}
			else if (is.getType() == Material.PAPER && e.getRecipe().getResult().getType() == Material.PAPER) {
				String resultLore = ChatUtils.stripColor(e.getRecipe().getResult().getItemMeta().getLore().get(0));
				String teaBaseLore = ChatUtils.stripColor(TeaBase.getLore());
				boolean isHasLore = is.getItemMeta().hasLore();

				// Crafting tea base
				if (isHasLore && resultLore.equals(teaBaseLore)) {
					player.sendMessage(ChatUtils.chatMessage("&cYou must use ordinary paper for this!"));
					e.setCancelled(true);
					return;
				}
				
				// Crafting tea bag with ordinary paper
				else if (!isHasLore && !resultLore.equals(teaBaseLore)) {
					player.sendMessage(ChatUtils.chatMessage("&cYou must use a tea base for this!"));
					e.setCancelled(true);
					return;
				}
				
				// Crafting tea bag with a tea bag
				else if (isHasLore && !ChatUtils.stripColor(is.getItemMeta().getLore().get(0)).equals(teaBaseLore)) {
					player.sendMessage(ChatUtils.chatMessage("&cYou must use a tea base for this!"));
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}
