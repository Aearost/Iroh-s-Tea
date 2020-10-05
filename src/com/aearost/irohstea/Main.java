package com.aearost.irohstea;


import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.commands.CommandTeas;
import com.aearost.commands.CommandTeasCompleter;
import com.aearost.events.TeaCrafting;
import com.aearost.events.TeaLeafDrop;
import com.aearost.recipes.GreenTeaRecipe;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		new Utils();
		
		// Initialize events	
		new TeaLeafDrop(this);
		new TeaCrafting(this);
		
		// Crafting Recipes
		new GreenTeaRecipe(this);
		
		
		// Initialize commands
		getCommand("teas").setExecutor(new CommandTeas());
		getCommand("teas").setTabCompleter(new CommandTeasCompleter());
	}
}