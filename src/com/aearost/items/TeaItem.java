package com.aearost.items;

import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;

public class TeaItem {

	private String name;
	private String teaBagName;
	private String lore;
	private PotionEffect pe;
	private Color c;
	
	public TeaItem(String name, String lore, PotionEffect pe, Color c) {
		this.name = name;
		this.teaBagName = name + " Bag";
		this.lore = lore;
		this.pe = pe;
		this.c = c;
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
		return pe;
	}
	
	public Color getColor() {
		return c;
	}
}
	