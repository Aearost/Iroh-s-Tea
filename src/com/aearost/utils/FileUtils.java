package com.aearost.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import com.aearost.items.Kettle;

public class FileUtils {

	/**
	 * Initializes the locationToKettles HashMap based on the kettles.json file.
	 * 
	 * After all kettles have been iterated, the file is deleted.
	 * 
	 * @param plugin
	 */
	public static void readFromKettleFile() {
		String currentPath = System.getProperty("user.dir");
		String filePath = currentPath + File.separator + "plugins" + File.separator + "IrohsTea" + File.separator
				+ "kettles.json";
		File file = new File(filePath);

		// First run of plugin
		if (!file.exists()) {
			return;
		}

		Scanner reader;
		try {
			reader = new Scanner(file);
			int fieldCount = 0;

			World world = null;
			int x = 0;
			int y = 0;
			int z = 0;
			boolean hasBottle = false;
			boolean hasTeaBag = false;
			ItemStack tea = null;

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String fieldName = null;
				String fieldValue = null;

				if (line.endsWith("\",")) {
					String[] parts = line.split("\"");
					fieldName = parts[1];
					fieldValue = parts[3];
				} else {
					continue;
				}

				if (fieldName.equals("worldName")) {
					world = Bukkit.getWorld(fieldValue);
				} else if (fieldName.equals("x")) {
					x = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("y")) {
					y = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("z")) {
					z = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("hasBottle")) {
					hasBottle = Boolean.parseBoolean(fieldValue);
				} else if (fieldName.equals("hasTeaBag")) {
					hasTeaBag = Boolean.parseBoolean(fieldValue);
				} else if (fieldName.equals("teaBagName")) {
					if (!fieldValue.equals("none")) {
						tea = ItemUtils.getItem(fieldValue + "_TEA");
					}
				} else {
					reader.close();
					throw new FileNotFoundException();
				}

				fieldCount++;

				if (fieldCount == 7) {
					Location location = new Location(world, x, y, z);
					Kettle kettle = null;
					if (hasTeaBag) {
						kettle = new Kettle(hasBottle, hasTeaBag, tea);
					} else {
						kettle = new Kettle(hasBottle, hasTeaBag);
					}
					KettleUtils.addKettle(location, kettle);
					fieldCount = 0;
				}
			}
			reader.close();
			file.delete();
		} catch (FileNotFoundException e) {
			Bukkit.getLogger().info("Something went wrong with reading the kettles!");
			e.printStackTrace();
		}
	}

	/**
	 * Writes to the kettles.json file based on the contents of the locationToKettle
	 * HashMap.
	 * 
	 * @param plugin
	 */
	public static void writeToKettleFile() {
		HashMap<Location, Kettle> locationToKettle = KettleUtils.getLocationToKettle();
		if (locationToKettle.size() > 0) {

			String currentPath = System.getProperty("user.dir");
			String filePath = currentPath + File.separator + "plugins" + File.separator + "IrohsTea" + File.separator
					+ "kettles.json";
			File pluginDirectory = new File(currentPath + File.separator + "plugins" + File.separator + "IrohsTea");
			File file = new File(filePath);

			// If the directory exists
			boolean isDirectoryCreated = true;
			if (!pluginDirectory.isDirectory()) {
				isDirectoryCreated = pluginDirectory.mkdir();
			}
			if (isDirectoryCreated) {
				try {
					// If the file isn't already there
					if (file.createNewFile()) {
						Bukkit.getLogger().info("A new kettles.json file has been generated");
					} else {
						throw new IOException();
					}
				} catch (IOException e) {
					Bukkit.getLogger().info("An error occured in the creation of kettles.json");
					e.printStackTrace();
				}

				try {
					FileWriter writer = new FileWriter(filePath);
					writer.write("{\n");
					writer.write("\"kettles\": {\n");
					int counter = 1;

					for (Map.Entry<Location, Kettle> entry : locationToKettle.entrySet()) {
						Location location = entry.getKey();
						Kettle kettle = KettleUtils.getKettle(location);

						int x = location.getBlockX();
						int y = location.getBlockY();
						int z = location.getBlockZ();
						String worldName = location.getWorld().getName();
						boolean hasBottle = kettle.getHasBottle();
						boolean hasTeaBag = kettle.getHasTeaBag();
						String teaBagName;

						// If only bottle is present, the tea field will be null
						if (hasTeaBag) {
							teaBagName = ItemUtils.getTeaName(kettle.getTea());
						} else {
							teaBagName = "none";
						}

						writer.write("    \"" + counter + "\": {\n");
						writer.write("        \"x\": \"" + x + "\",\n");
						writer.write("        \"y\": \"" + y + "\",\n");
						writer.write("        \"z\": \"" + z + "\",\n");
						writer.write("        \"worldName\": \"" + worldName + "\",\n");
						writer.write("        \"hasBottle\": \"" + hasBottle + "\",\n");
						writer.write("        \"hasTeaBag\": \"" + hasTeaBag + "\",\n");
						writer.write("        \"teaBagName\": \"" + teaBagName + "\",\n");

						// If it's the last entry
						if (locationToKettle.size() == counter) {
							writer.write("    }\n");
						} else {
							writer.write("    },\n");
						}
						counter++;
					}
					writer.write("}\n");
					writer.close();
				} catch (IOException e) {
					Bukkit.getLogger().info("There was an error in saving the kettles");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Initializes the locationToPlant HashMap based on the tea_plants.json file.
	 * 
	 * After all tea plants have been iterated, the file is deleted.
	 * 
	 * @param plugin
	 */
	public static void readFromTeaPlantFile() {
		String currentPath = System.getProperty("user.dir");
		String filePath = currentPath + File.separator + "plugins" + File.separator + "IrohsTea" + File.separator
				+ "tea_plants.json";
		File file = new File(filePath);

		// First run of plugin
		if (!file.exists()) {
			return;
		}

		Scanner reader;
		try {
			reader = new Scanner(file);
			int fieldCount = 0;

			World world = null;
			int x = 0;
			int y = 0;
			int z = 0;
			boolean isGrown = false;

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String fieldName = null;
				String fieldValue = null;

				if (line.endsWith("\",")) {
					String[] parts = line.split("\"");
					fieldName = parts[1];
					fieldValue = parts[3];
				} else {
					continue;
				}

				if (fieldName.equals("worldName")) {
					world = Bukkit.getWorld(fieldValue);
				} else if (fieldName.equals("x")) {
					x = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("y")) {
					y = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("z")) {
					z = Integer.parseInt(fieldValue);
				} else if (fieldName.equals("isGrown")) {
					isGrown = Boolean.parseBoolean(fieldValue);
				} else {
					reader.close();
					throw new FileNotFoundException();
				}

				fieldCount++;

				if (fieldCount == 5) {
					Location location = new Location(world, x, y, z);
					TeaPlantUtils.addPlant(location, isGrown);
					fieldCount = 0;
				}
			}
			reader.close();
			file.delete();
		} catch (FileNotFoundException e) {
			Bukkit.getLogger().info("Something went wrong with reading the tea plants!");
			e.printStackTrace();
		}
	}

	/**
	 * Writes to the tea_plants.json file based on the contents of the
	 * locationToPlant HashMap.
	 * 
	 * @param plugin
	 */
	public static void writeToTeaPlantFile() {
		HashMap<Location, Boolean> locationToPlant = TeaPlantUtils.getLocationToPlant();
		if (locationToPlant.size() > 0) {

			String currentPath = System.getProperty("user.dir");
			String filePath = currentPath + File.separator + "plugins" + File.separator + "IrohsTea" + File.separator
					+ "tea_plants.json";
			File pluginDirectory = new File(currentPath + File.separator + "plugins" + File.separator + "IrohsTea");
			File file = new File(filePath);

			// If the directory exists
			boolean isDirectoryCreated = true;
			if (!pluginDirectory.isDirectory()) {
				isDirectoryCreated = pluginDirectory.mkdir();
			}
			if (isDirectoryCreated) {
				try {
					// If the file isn't already there
					if (file.createNewFile()) {
						Bukkit.getLogger().info("A new tea_plants.json file has been generated");
					} else {
						throw new IOException();
					}
				} catch (IOException e) {
					Bukkit.getLogger().info("An error occured in the creation of tea_plants.json");
					e.printStackTrace();
				}

				try {
					FileWriter writer = new FileWriter(filePath);
					writer.write("{\n");
					writer.write("\"plants\": {\n");
					int counter = 1;

					for (Map.Entry<Location, Boolean> entry : locationToPlant.entrySet()) {
						Location location = entry.getKey();

						int x = location.getBlockX();
						int y = location.getBlockY();
						int z = location.getBlockZ();
						String worldName = location.getWorld().getName();
						boolean isGrown = TeaPlantUtils.isPlantGrown(location);

						writer.write("    \"" + counter + "\": {\n");
						writer.write("        \"x\": \"" + x + "\",\n");
						writer.write("        \"y\": \"" + y + "\",\n");
						writer.write("        \"z\": \"" + z + "\",\n");
						writer.write("        \"worldName\": \"" + worldName + "\",\n");
						writer.write("        \"isGrown\": \"" + isGrown + "\",\n");

						// If it's the last entry
						if (locationToPlant.size() == counter) {
							writer.write("    }\n");
						} else {
							writer.write("    },\n");
						}
						counter++;
					}
					writer.write("}\n");
					writer.close();
				} catch (IOException e) {
					Bukkit.getLogger().info("There was an error in saving the tea plants");
					e.printStackTrace();
				}
			}
		}
	}

}
