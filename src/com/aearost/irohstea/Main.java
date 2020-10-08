package com.aearost.irohstea;


import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.commands.CommandTeas;
import com.aearost.commands.CommandTeasCompleter;
import com.aearost.events.CauldronBrewing;
import com.aearost.events.CauldronDestroy;
import com.aearost.events.TeaCrafting;
import com.aearost.events.TeaLeafDrop;
import com.aearost.recipes.CactusJuiceRecipe;
import com.aearost.recipes.GreenTeaRecipe;
import com.aearost.recipes.JasmineTeaRecipe;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		new Utils();
		
		// Initialize events	
		new TeaLeafDrop(this);
		new TeaCrafting(this);
		new CauldronBrewing(this);
		new CauldronDestroy(this);
		
		// Crafting Recipes
		new GreenTeaRecipe(this);
		new JasmineTeaRecipe(this);
		new CactusJuiceRecipe(this);
		
		
		// Initialize commands
		getCommand("teas").setExecutor(new CommandTeas());
		getCommand("teas").setTabCompleter(new CommandTeasCompleter());
	}
}