package com.aearost.events;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class TeaPlantGrow {

	private Plugin plugin;
	
	public TeaPlantGrow(Plugin plugin) {
		this.plugin = plugin;
		doTicks();
	}
	
	private void doTicks() {
		BukkitScheduler scheduler = plugin.getServer().getScheduler();
	    scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
	        @Override
	        public void run() {
            	
	        }
	    }, 0L, 20L);
	}
	
}
