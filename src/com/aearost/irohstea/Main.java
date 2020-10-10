package com.aearost.irohstea;


import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.commands.CommandTeas;
import com.aearost.commands.CommandTeasCompleter;
import com.aearost.events.CauldronBrewing;
import com.aearost.events.CauldronDestroy;
import com.aearost.events.TeaCrafting;
import com.aearost.events.TeaLeafDrop;
import com.aearost.recipes.AppleCinnamonTeaRecipe;
import com.aearost.recipes.CactusJuiceRecipe;
import com.aearost.recipes.ChocolateTeaRecipe;
import com.aearost.recipes.GreenTeaRecipe;
import com.aearost.recipes.JasmineTeaRecipe;
import com.aearost.recipes.MatchaTeaRecipe;
import com.aearost.recipes.T8TeaRecipe;
import com.aearost.recipes.WhiteDragonTeaRecipe;
import com.aearost.recipes.WhiteJadeTeaRecipe;

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
		new AppleCinnamonTeaRecipe(this);
		new ChocolateTeaRecipe(this);
		new GreenTeaRecipe(this);
		new JasmineTeaRecipe(this);
		new MatchaTeaRecipe(this);
		new T8TeaRecipe(this);
		new WhiteDragonTeaRecipe(this);
		new WhiteJadeTeaRecipe(this);
		
		new CactusJuiceRecipe(this);
		
		
		
		// Initialize commands
		getCommand("teas").setExecutor(new CommandTeas());
		getCommand("teas").setTabCompleter(new CommandTeasCompleter());
	}
}