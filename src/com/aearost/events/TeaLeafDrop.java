package com.aearost.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.Main;
import com.aearost.items.TeaLeaf;
import com.aearost.utils.Utils;

public class TeaLeafDrop implements Listener {

	public TeaLeafDrop(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Handles the dropping of tea leaves when destroying grass.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onGrassDestroy(final BlockBreakEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		
		boolean teaLeafWillDrop = false;
		if (block.getType() == Material.GRASS) {
			teaLeafWillDrop = determineIfDropTea(1);
		} else if (block.getType() == Material.TALL_GRASS) {
			teaLeafWillDrop = determineIfDropTea(Utils.DROP_RATE_MODIFIER);
		}
		
		if (teaLeafWillDrop) {
			ItemStack teaLeaf = TeaLeaf.getTeaLeaf();
			block.getWorld().dropItemNaturally(location, teaLeaf);
		}
	}
	
	/**
	 * Determines whether or not to drop a tea leaf.
	 * @param modifier
	 * @return
	 */
	private boolean determineIfDropTea(int modifier) {
		return new Random().nextInt(100) + 1 < (4 * modifier);
	}
}
