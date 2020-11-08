package com.aearost.irohsteas;

import org.bukkit.plugin.java.JavaPlugin;

import com.aearost.irohsteas.commands.CommandTeas;
import com.aearost.irohsteas.commands.CommandTeasCompleter;
import com.aearost.irohsteas.events.CauldronBrewing;
import com.aearost.irohsteas.events.CauldronDestroy;
import com.aearost.irohsteas.events.TeaCrafting;
import com.aearost.irohsteas.events.TeaPlantDecay;
import com.aearost.irohsteas.events.TeaPlantDrop;
import com.aearost.irohsteas.events.TeaPlantGrow;
import com.aearost.irohsteas.events.TeaPlantHarvest;
import com.aearost.irohsteas.events.TeaPlantPlace;
import com.aearost.irohsteas.recipes.AppleCinnamonTeaRecipe;
import com.aearost.irohsteas.recipes.CactusJuiceRecipe;
import com.aearost.irohsteas.recipes.ChamomileTeaRecipe;
import com.aearost.irohsteas.recipes.ChocolateTeaRecipe;
import com.aearost.irohsteas.recipes.GreenTeaRecipe;
import com.aearost.irohsteas.recipes.HoneyRoseTeaRecipe;
import com.aearost.irohsteas.recipes.JasmineTeaRecipe;
import com.aearost.irohsteas.recipes.MatchaTeaRecipe;
import com.aearost.irohsteas.recipes.SuspiciousTeaRecipe;
import com.aearost.irohsteas.recipes.T8TeaRecipe;
import com.aearost.irohsteas.recipes.TeaBaseRecipe;
import com.aearost.irohsteas.recipes.TulipTeaRecipe;
import com.aearost.irohsteas.recipes.WhiteDragonTeaRecipe;
import com.aearost.irohsteas.recipes.WhiteJadeTeaRecipe;
import com.aearost.irohsteas.utils.ItemUtils;
import com.aearost.irohsteas.utils.KettleUtils;
import com.aearost.irohsteas.utils.TeaPlantUtils;

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
		new TeaBaseRecipe(this);
		new AppleCinnamonTeaRecipe(this);
		new ChamomileTeaRecipe(this);
		new ChocolateTeaRecipe(this);
		new GreenTeaRecipe(this);
		new HoneyRoseTeaRecipe(this);
		new JasmineTeaRecipe(this);
		new MatchaTeaRecipe(this);
		new SuspiciousTeaRecipe(this);
		new TulipTeaRecipe(this);
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