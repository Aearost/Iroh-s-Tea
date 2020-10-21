package com.aearost;


import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.commands.CommandTeas;
import com.aearost.commands.CommandTeasCompleter;
import com.aearost.events.CauldronBrewing;
import com.aearost.events.CauldronDestroy;
import com.aearost.events.TeaCrafting;
import com.aearost.events.TeaPlantDecay;
import com.aearost.events.TeaPlantDrop;
import com.aearost.events.TeaPlantGrow;
import com.aearost.events.TeaPlantHarvest;
import com.aearost.events.TeaPlantPlace;
import com.aearost.recipes.AppleCinnamonTeaRecipe;
import com.aearost.recipes.CactusJuiceRecipe;
import com.aearost.recipes.ChocolateTeaRecipe;
import com.aearost.recipes.GreenTeaRecipe;
import com.aearost.recipes.JasmineTeaRecipe;
import com.aearost.recipes.MatchaTeaRecipe;
import com.aearost.recipes.T8TeaRecipe;
import com.aearost.recipes.WhiteDragonTeaRecipe;
import com.aearost.recipes.WhiteJadeTeaRecipe;
import com.aearost.utils.ItemUtils;
import com.aearost.utils.KettleUtils;
import com.aearost.utils.TeaPlantUtils;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		// Initialize Utils
		new ItemUtils();
		new KettleUtils(true);
		new TeaPlantUtils(true);
		
		// Initialize events	
		new TeaPlantDrop(this);
		new TeaPlantPlace(this);
		new TeaPlantGrow(this);
		new TeaPlantHarvest(this);
		new TeaPlantDecay(this);
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
	
	@Override
	public void onDisable() {
		new KettleUtils(false);
		new TeaPlantUtils(false);
	}
	
}