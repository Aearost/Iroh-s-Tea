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

public class TeaLeafDrop implements Listener {

	public TeaLeafDrop(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onGrassDestroy(final BlockBreakEvent e) {
		Block b = e.getBlock();
		Location l = b.getLocation();
		
		boolean teaLeafWillDrop = false;
		if (b.getType() == Material.GRASS) {
			teaLeafWillDrop = determineIfDropTea(1);
		} else if (b.getType() == Material.TALL_GRASS) {
			teaLeafWillDrop = determineIfDropTea(2);
		}
		if (teaLeafWillDrop) {
			ItemStack teaLeaf = TeaLeaf.getTeaLeaf();
			b.getWorld().dropItemNaturally(l, teaLeaf);
		}
	}
	
	private boolean determineIfDropTea(int modifier) {
		return new Random().nextInt(100) + 1 < (4 * modifier);
	}
}
