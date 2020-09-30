package com.aearost.commands;

import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.TeaLeaf;
import com.aearost.irohstea.Utils;

public class CommandTeas implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Utils.PREFIX + Utils.chat("&fYou're missing parameters!"));
			return false;
		}
		
		if (args.length >= 2) {
			if (args[0].equals("give")) {
				if (args[1].equals("TEA_LEAF")) {
					if (args.length == 2) {
						if (sender instanceof Player) {
							return giveItem(TeaLeaf.getTeaLeaf(), (Player) sender, sender);
						} else {
							sender.sendMessage(Utils.chat(Utils.PREFIX + "&cYou must be a player to use this command!"));
						}
					}
					else if (args.length == 3) {
						Player target = Bukkit.getPlayer(args[2]);
						return giveItem(TeaLeaf.getTeaLeaf(), target, sender);
					}
				}
			}
		}
		return false;
	}
	
	private boolean giveItem(ItemStack itemToAdd, Player target, CommandSender sender) {
		if (target != null) {
			if (Utils.hasInventorySpace(target)) {
				ListIterator<ItemStack> iterator = target.getInventory().iterator();
				// Checks if the ItemStack already is in the inventory
				while (iterator.hasNext()) {
					target.sendMessage("----------------");
					int iteratedIndex = iterator.nextIndex();
					ItemStack is = iterator.next();
					
					target.sendMessage("iteratedIndex: " + iteratedIndex);
					if (is != null) {
						target.sendMessage("is: " + is.getAmount() + " " + is.getType());
						target.sendMessage("itemToAdd: " + itemToAdd.getAmount() + " " + itemToAdd.getType());
						target.sendMessage("isMatchingItemStack: " + Utils.isMatchingItemStack(is,  itemToAdd));
						target.sendMessage("Is full stack: " + (is.getAmount() < is.getMaxStackSize()));
					}
					
					
					// If it's the same item
					if (is != null && is.getType() == itemToAdd.getType() && Utils.isMatchingItemStack(is, itemToAdd)) {
						// If the ItemStack isn't full
						if (is.getAmount() < is.getMaxStackSize()) {
							is.setAmount(is.getAmount() + 1);
							target.getInventory().setItem(iteratedIndex, is);
							return true;
						}
					}
				}
				target.getInventory().addItem(TeaLeaf.getTeaLeaf());
				return true;
			} else {
				sender.sendMessage(Utils.PREFIX + Utils.chat("&e" + target.getName() + "'s &cinventory is full!"));
			}
		} else {
			sender.sendMessage(Utils.PREFIX + Utils.chat("&cThe player could not be found."));
		}
		return false;
	}

}