package com.aearost.utils;

import java.util.HashMap;

import org.bukkit.Location;

public class TeaPlantUtils {

	public final static HashMap<Location, Boolean> locationToPlant = new HashMap<Location, Boolean>();
	
	public TeaPlantUtils(boolean isServerStarting) {
		if (isServerStarting) {
			FileUtils.readFromTeaPlantFile();
		} else {
			FileUtils.writeToTeaPlantFile();
		}

	}
	
	public static boolean isPlant(Location location) {
		return locationToPlant.containsKey(location);
	}

	public static boolean isPlantGrown(Location location) {
		return locationToPlant.get(location);
	}

	public static void addPlant(Location location, boolean isGrown) {
		locationToPlant.put(location, isGrown);
	}

	public static void removePlant(Location location) {
		locationToPlant.remove(location);
	}

	public static HashMap<Location, Boolean> getLocationToPlant() {
		return locationToPlant;
	}
	
}
