package com.aearost.items;

import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;

/**
 * TeaItems are used to store all data regarding a tea.
 * 
 * @author Aearost
 *
 */
public class TeaItem {

	private String name;
	private String teaBagName;
	private String lore;
	private PotionEffect potionEffect;
	private Color color;

	public TeaItem(String name, String lore, PotionEffect pe, Color c) {
		this.name = name;
		this.teaBagName = name + " Bag";
		this.lore = lore;
		this.potionEffect = pe;
		this.color = c;
	}

	public String getName() {
		return name;
	}

	public String getTeaBagName() {
		return teaBagName;
	}

	public String getLore() {
		return lore;
	}

	public PotionEffect getPotionEffect() {
		return potionEffect;
	}

	public Color getColor() {
		return color;
	}
}
