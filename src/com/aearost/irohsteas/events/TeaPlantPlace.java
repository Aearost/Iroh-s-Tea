package com.aearost.irohsteas.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohsteas.Main;
import com.aearost.irohsteas.utils.TeaPlantUtils;

public class TeaPlantPlace implements Listener {

	public TeaPlantPlace(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Handles the placing of a tea plant.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onTeaPlantPlace(final BlockPlaceEvent e) {
		Block block = e.getBlock();
		ItemStack is = e.getItemInHand();
		if (TeaPlantUtils.isTeaPlant(is)) {
			Location location = block.getLocation();
			TeaPlantUtils.addPlant(location, false);
		}
	}
}
