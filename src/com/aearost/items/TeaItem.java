package com.aearost.items;

import java.util.ArrayList;
import java.util.List;

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
	private List<PotionEffect> potionEffects;
	private Color color;

	public TeaItem(String name, String lore, PotionEffect potionEffect, Color color) {
		this.name = name;
		this.teaBagName = name + " Bag";
		this.lore = lore;
		this.potionEffects = new ArrayList<>();
		this.potionEffects.add(potionEffect);
		this.color = color;
	}
	
	public TeaItem(String name, String lore, List<PotionEffect> potionEffects, Color color) {
		this.name = name;
		this.teaBagName = name + " Bag";
		this.lore = lore;
		this.potionEffects = potionEffects;
		this.color = color;
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

	public List<PotionEffect> getPotionEffects() {
		return potionEffects;
	}

	public Color getColor() {
		return color;
	}
}
