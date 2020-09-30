package com.aearost.irohstea;


import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.commands.CommandTeas;
import com.aearost.commands.CommandTeasCompleter;
import com.aearost.events.TeaLeafDrop;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		// Initialize events	
		new TeaLeafDrop(this);
		
		
		// Initialize commands
		getCommand("teas").setExecutor(new CommandTeas());
		getCommand("teas").setTabCompleter(new CommandTeasCompleter());
	}
}