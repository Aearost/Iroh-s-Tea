package com.aearost.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.aearost.Main;
import com.aearost.items.TeaLeaf;
import com.aearost.items.TeaPlant;
import com.aearost.utils.TeaPlantUtils;

public class TeaPlantHarvest implements Listener {

	public TeaPlantHarvest(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Handles the harvesting of tea plants by destroying them.
	 * 
	 * Additionally will drop the tea plant's drops if the block beneath it is destroyed.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onTeaPlantHarvest(final BlockBreakEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		Location locationAbove = new Location(location.getWorld(), location.getBlockX(), location.getBlockY() + 1,
				location.getBlockZ());

		if (TeaPlantUtils.isPlant(location)) {
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				e.setCancelled(true);
				harvestTeaPlant(block, location);
			}
		} else if (TeaPlantUtils.isPlant(locationAbove)) {
			harvestTeaPlant(locationAbove.getBlock(), locationAbove);
			block.setType(Material.AIR);
			TeaPlantUtils.removePlant(location);
		}
	}

	/**
	 * Handles the harvesting of tea plants by right-clicking them.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onTeaPlantInteract(final PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block != null) {
				// Only harvest if it's leaves
				if (block.getType() == Material.JUNGLE_LEAVES) {
					Location location = block.getLocation();

					if (TeaPlantUtils.isPlant(location)) {
						harvestTeaPlant(block, location);
					}
				}
			}
		}
	}

	/**
	 * Handles the actual harvesting of a tea plant.
	 * 
	 * @param block
	 * @param location
	 */
	public static void harvestTeaPlant(Block block, Location location) {
		// If it has not yet grown
		if (block.getType() == Material.OAK_SAPLING) {
			location.getWorld().dropItemNaturally(location, TeaPlant.getTeaPlant());
			block.setType(Material.AIR);
			TeaPlantUtils.removePlant(location);
		} else {
			ItemStack teaLeaves = TeaLeaf.getTeaLeaf();
			teaLeaves.setAmount(determineDropAmount(true));
			location.getWorld().dropItemNaturally(location, teaLeaves);
			ItemStack teaPlants = TeaPlant.getTeaPlant();
			int teaPlantDropAmount = determineDropAmount(false);
			if (teaPlantDropAmount > 0) {
				teaPlants.setAmount(teaPlantDropAmount);
				location.getWorld().dropItemNaturally(location, teaPlants);
			}
			// Automatically replants it, no need to remove the block
			block.setType(Material.OAK_SAPLING);
			TeaPlantUtils.addPlant(location, false);
		}
	}

	/**
	 * Randomly the amount of tea leaves or tea plants to be dropped upon harvesting
	 * a fully grown tea plant.
	 * 
	 * @param isTeaLeaves
	 * @return
	 */
	private static int determineDropAmount(boolean isTeaLeaves) {
		Random random = new Random();
		int amount = random.nextInt(10) + 1;
		if (isTeaLeaves) {
			if (amount < 4) {
				return 2;
			} else if (amount < 9) {
				return 3;
			} else {
				return 4;
			}
		} else {
			if (amount < 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}
