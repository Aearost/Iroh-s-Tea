package com.aearost.utils;

import java.util.HashMap;

import org.bukkit.Location;

import com.aearost.items.Kettle;

public class KettleUtils {

	public final static HashMap<Location, Kettle> locationToKettle = new HashMap<Location, Kettle>();

	public KettleUtils(boolean isServerStarting) {
		if (isServerStarting) {
			FileUtils.readFromKettleFile();
		} else {
			FileUtils.writeToKettleFile();
		}

	}

	public static Kettle getKettle(Location location) {
		return locationToKettle.get(location);
	}

	public static void addKettle(Location location, Kettle kettle) {
		locationToKettle.put(location, kettle);
	}

	public static void removeKettle(Location location) {
		locationToKettle.remove(location);
	}

	public static HashMap<Location, Kettle> getLocationToKettle() {
		return locationToKettle;
	}

}
