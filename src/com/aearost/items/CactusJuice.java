package com.aearost.items;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.aearost.utils.ChatUtils;

public class CactusJuice {

	/**
	 * Returns an ItemStack of Cactus Juice.
	 * 
	 * @return
	 */
	public static ItemStack getCactusJuice() {
		ItemStack juice = new ItemStack(Material.POTION, 1);
		PotionMeta meta = (PotionMeta) juice.getItemMeta();
		meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 2400, 4), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 3600, 2), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3600, 4), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 0), true);
		meta.setColor(Color.YELLOW);
		juice.setItemMeta(meta);
		ArrayList<String> s = new ArrayList<>();
		meta.setDisplayName(ChatUtils.translateToColor("&2Cactus Juice"));
 	    s.add(ChatUtils.translateToColor("&a&oDesperate times call for desperate measures..."));
 	    meta.setLore(s);
 	    juice.setItemMeta(meta);
		return juice;
	}
	
}