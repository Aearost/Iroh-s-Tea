package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.Main;
import com.aearost.items.Items;
import com.aearost.items.Kettle;
import com.aearost.items.TeaBag;
import com.aearost.utils.ItemUtils;
import com.aearost.utils.KettleUtils;

public class CauldronDestroy implements Listener {

	public CauldronDestroy(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Handles the destroying of a cauldron.
	 * 
	 * If the cauldron contained an item, it will be dropped as well.
	 * 
	 * @param e
	 */
	@EventHandler
	public void onCauldronDestroy(final BlockBreakEvent e) {
		Block block = e.getBlock();
		if (block.getType() == Material.CAULDRON) {
			Location location = block.getLocation();
			Kettle kettle = KettleUtils.getKettle(location);
			
			if (kettle == null) {
				return;
			}
			
			if (kettle.getHasBottle()) {
				location.getWorld().dropItemNaturally(location, new ItemStack(Material.GLASS_BOTTLE, 1));
			} else if (kettle.getHasTeaBag()) {
				location.getWorld().dropItemNaturally(location, TeaBag.getTeaBag(Items.valueOf(ItemUtils.getTeaName(kettle.getTea()) + "_TEA")));
			}
			KettleUtils.removeKettle(location);
		}
	}
	
}
