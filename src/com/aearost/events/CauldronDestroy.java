package com.aearost.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.Main;
import com.aearost.irohstea.Utils;
import com.aearost.items.CauldronInfo;
import com.aearost.items.Items;
import com.aearost.items.TeaBag;

public class CauldronDestroy implements Listener {

	public CauldronDestroy(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCauldronDestroy(final BlockBreakEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.CAULDRON) {
			Location l = b.getLocation();
			CauldronInfo ci = Utils.getCauldronInfo(l);
			
			if (ci == null) {
				return;
			}
			
			if (ci.getHasBottle()) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.GLASS_BOTTLE, 1));
			} else if (ci.getHasTeaBag()) {
				l.getWorld().dropItemNaturally(l, TeaBag.getTeaBag(Items.valueOf(Utils.getTeaName(ci.getTea()) + "_TEA")));
			}
			Utils.removeCauldronInfo(l);
		}
	}
	
}
