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

	public static Kettle getKettle(Location l) {
		return locationToKettle.get(l);
	}

	public static void addKettle(Location l, Kettle ci) {
		locationToKettle.put(l, ci);
	}

	public static void removeKettle(Location l) {
		locationToKettle.remove(l);
	}

	public static HashMap<Location, Kettle> getLocationToKettle() {
		return locationToKettle;
	}

}
