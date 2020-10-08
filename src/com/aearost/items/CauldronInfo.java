package com.aearost.items;

import org.bukkit.inventory.ItemStack;

public class CauldronInfo {
	
	private boolean hasBottle;
	private boolean hasTeaBag;
	private ItemStack tea;
	
	public CauldronInfo(boolean hasBottle, boolean hasTeaBag) {
		this.hasBottle = hasBottle;
		this.hasTeaBag = hasTeaBag;
	}
	
	public boolean getHasBottle() {
		return this.hasBottle;
	}
	
	public void setHasBottle(boolean hasBottle) {
		this.hasBottle = hasBottle;
	}
	
	public boolean getHasTeaBag() {
		return this.hasTeaBag;
	}
	
	public void setHasTeaBag(boolean hasTeaBag) {
		this.hasTeaBag = hasTeaBag;
	}
	
	public ItemStack getTea() {
		return this.tea;
	}
	
	public void setTea(ItemStack tea) {
		this.tea = tea;
	}
}
