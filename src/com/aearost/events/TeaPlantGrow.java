package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

import com.aearost.Main;
import com.aearost.utils.TeaPlantUtils;

public class TeaPlantGrow implements Listener {

	public TeaPlantGrow(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Prevents a tree from growing and instead grows the tea plant.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onTeaPlantGrow(final StructureGrowEvent e) {
		Location location = e.getLocation();
		if (TeaPlantUtils.isPlant(location)) {
			e.setCancelled(true);
			location.getBlock().setType(Material.JUNGLE_LEAVES);
			TeaPlantUtils.setPlantGrown(location, true);
		}
	}

}
