package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.aearost.Main;

public class TeaPlantPlace implements Listener {

	public TeaPlantPlace(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onTeaPlantPlace(final BlockPlaceEvent e) {
		
	}
	
}
