package com.aearost.items;

import org.bukkit.inventory.ItemStack;

import com.aearost.utils.ItemUtils;

/**
 * Kettles are used to distinguish an ordinary cauldron from one that stores
 * data related to teas.
 * 
 * @author Aearost
 *
 */
public class Kettle {

	private boolean hasBottle;
	private boolean hasTeaBag;
	private ItemStack tea;

	public Kettle(boolean hasBottle, boolean hasTeaBag) {
		this.hasBottle = hasBottle;
		this.hasTeaBag = hasTeaBag;
	}

	public Kettle(boolean hasBottle, boolean hasTeaBag, ItemStack tea) {
		this.hasBottle = hasBottle;
		this.hasTeaBag = hasTeaBag;
		this.tea = tea;
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

	@Override
	public String toString() {
		return !hasTeaBag ? "hasBottle: " + hasBottle + " | hasTeaBag: " + hasTeaBag
				: "hasBottle: " + hasBottle + " | hasTeaBag: " + hasTeaBag + " | tea: " + ItemUtils.getTeaName(tea);
	}
}
