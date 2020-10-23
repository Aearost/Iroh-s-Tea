package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

import com.aearost.Main;
import com.aearost.utils.TeaPlantUtils;

public class TeaPlantDecay implements Listener {

	public TeaPlantDecay(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Prevents tea plants from decaying.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onTeaPlantDecay(final LeavesDecayEvent e) {
		Location location = e.getBlock().getLocation();
		if (TeaPlantUtils.isPlant(location)) {
			e.setCancelled(true);
		}
	}

}
