package com.aearost.irohsteas.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohsteas.Main;
import com.aearost.irohsteas.items.TeaPlant;
import com.aearost.irohsteas.utils.Utils;

public class TeaPlantDrop implements Listener {

	public TeaPlantDrop(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Handles the dropping of tea plants when destroying grass.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onGrassDestroy(final BlockBreakEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		
		boolean teaPlantWillDrop = false;
		if (block.getType() == Material.GRASS || block.getType() == Material.FERN) {
			teaPlantWillDrop = determineIfDropTeaPlant(1);
		} else if (block.getType() == Material.TALL_GRASS || block.getType() == Material.LARGE_FERN) {
			teaPlantWillDrop = determineIfDropTeaPlant(Utils.DROP_RATE_MODIFIER);
		}
		
		if (teaPlantWillDrop) {
			ItemStack teaPlant = TeaPlant.getTeaPlant();
			block.getWorld().dropItemNaturally(location, teaPlant);
		}
	}
	
	/**
	 * Determines whether or not to drop a tea plant.
	 * 
	 * @param modifier
	 * @return
	 */
	private boolean determineIfDropTeaPlant(int modifier) {
		return new Random().nextInt(100) + 1 < (4 * modifier);
	}
}
