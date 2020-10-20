package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.aearost.Main;

public class TeaPlantHarvest implements Listener {

	// Handle both left click destroy and right click harvest
	public TeaPlantHarvest(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onTeaPlantHarvest(final BlockBreakEvent e) {
		
	}
	
	@EventHandler
	public void onTeaPlantInteract(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		
		if (b != null) {
			
		}
	}
	
}
