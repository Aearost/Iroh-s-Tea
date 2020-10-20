package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.aearost.utils.ChatUtils;

public class RecipeBook {
	
	public static ItemStack getRecipeBook() {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle(ChatUtils.translateToColor("&2A Guide to Happiness"));
		meta.setAuthor(ChatUtils.translateToColor("&8Uncle Iroh"));
		ArrayList<String> pages = new ArrayList<String>();
		
		// Maximum of 256 characters per page!
		pages.add(ChatUtils.translateToColor("&l&nTea Leaves\n\n&rWithout a tea leaf, one cannot make tea!\n\nTo get these leaves, destroy tall grass for a chance of them being dropped like seeds, and use them as a crafting ingredient in tea bag recipes!"));
		pages.add(ChatUtils.translateToColor("&l&nKettles\n\n&rTea cannot be brewed without a kettle.\n\nIn order to create one, you must use a cauldron and place a flame beneath it. Fill the cauldron with water, and right click it with an empty bottle and the tea bag of your choice!"));
		pages.add(ChatUtils.translateToColor("&l&nGreen Tea\n\n&r&oSimple, yet delicious\n\n&rIngredients:\n- 1 tea leaf\n\nEffects:\n- Luck III (30s)"));
		pages.add(ChatUtils.translateToColor("&l&nApple Cinnamon Tea\n\n&r&oJust a hint of tree bark\n\n&rIngredients:\n- 1 tea leaf\n- 1 apple\n- 1 wheat seeds\n- 1 dark oak log\n\nEffects:\n- Haste II (180s)"));
		pages.add(ChatUtils.translateToColor("&l&nChocolate Tea\n\n&r&oSweet goodness!\n\n&rIngredients:\n- 1 tea leaf\n- 1 milk bucket\n- 1 cocoa beans\n- 1 sugar\n\nEffects:\n- Speed IV (120s)"));
		pages.add(ChatUtils.translateToColor("&l&nJasmine Tea\n\n&r&oThe sweet taste of blossoms\n\n&rIngredients:\n- 1 tea leaf\n- 1 white tulip\n- 1 peony\n\nEffects:\n- Invisibility (15s)"));
		pages.add(ChatUtils.translateToColor("&l&nMatcha Tea\n\n&r&oThe Japanese flavor you needed\n\n&rIngredients:\n- 1 tea leaf\n- 1 sugar\n- 1 leaves (any kind)\n\nEffects:\n- Jump III (120s)"));
		pages.add(ChatUtils.translateToColor("&l&nT8 Tea\n\n&r&oEver hear of V8?\n\n&rIngredients:\n- 1 tea leaf\n- 2 carrots\n- 2 beetroots\n- 2 sweet berries\n- 1 melon slice\n\nEffects:\n- Regeneration II (60s)"));
		pages.add(ChatUtils.translateToColor("&l&nWhite Dragon Tea\n\n&r&oDelicious tea?\n\nIngredients:\n- 1 tea leaf\n- 1 azure bluet\n- 1 white tulip\n\nEffects:\n- Instant Health III"));
		pages.add(ChatUtils.translateToColor("&l&nWhite Jade Tea\n\n&r&oDeadly poison?\n\nIngredients:\n- 1 tea leaf\n- 1 lily of the valley\n- 1 white tulip\n\nEffects:\n- Slowness III (180s)"));
		
		pages.add(ChatUtils.translateToColor("&l&nCactus Juice\n\n&r&oDesperate times call for desperate measures...\n\n&rIngredients:\n - 4 cactus\n\nEffects:\n- Nausea V (120s)\n- Slowness III (180s)\n- Weakness V (180s)\n- Night Vision (10s)"));
		
		meta.setPages(pages);
		
		book.setItemMeta(meta);
		return book;
	}
	
}
