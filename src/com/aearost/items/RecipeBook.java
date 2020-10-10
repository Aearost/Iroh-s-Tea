package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.aearost.irohstea.Utils;

public class RecipeBook {
	
	public static ItemStack getRecipeBook() {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setTitle(Utils.translateToColor("&2A Guide to Happiness"));
		bm.setAuthor(Utils.translateToColor("&8Uncle Iroh"));
		ArrayList<String> pages = new ArrayList<String>();
		
		// Maximum of 256 characters per page!
		pages.add(Utils.translateToColor("&l&nTea Leaves\n\n&rWithout a tea leaf, one cannot make tea!\n\nTo get these leaves, destroy tall grass for a chance of them being dropped like seeds, and use them as a crafting ingredient in tea bag recipes!"));
		pages.add(Utils.translateToColor("&l&nKettles\n\n&rTea cannot be brewed without a kettle.\n\nIn order to create one, you must use a cauldron and place a flame beneath it. Fill the cauldron with water, and right click it with an empty bottle and the tea bag of your choice!"));
		pages.add(Utils.translateToColor("&l&nGreen Tea\n\n&r&oSimple, yet delicious\n\n&rIngredients:\n- 1 tea leaf\n\nEffects:\n- Luck III (30s)"));
		pages.add(Utils.translateToColor("&l&nJasmine Tea\n\n&r&oThe sweet taste of blossoms\n\n&rIngredients:\n- 1 tea leaf\n- 1 white tulip\n- 1 peony\n\nEffects:\n- Speed III (60s)"));
		pages.add(Utils.translateToColor("&l&nCactus Juice\n\n&r&oDesperate times call for desperate measures...\n\n&rIngredients:\n - 4 cactus\n\nEffects:\n- Nausea V (120s)\n- Slowness III (180s)\n- Weakness V (180s)\n- Night Vision (10s)"));
		
		bm.setPages(pages);
		
		book.setItemMeta(bm);
		return book;
	}
	
}
