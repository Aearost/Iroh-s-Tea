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
			sender.sendMessage(Utils.chatMessage("&fYou're missing parameters!"));
			return false;
		}

		// /teas give Aearost TEA_LEAF 7

		if (args.length >= 3 && args[0].equals("give")) {
			Player target = Bukkit.getPlayer(args[1]);
			
			if (args.length == 3) {
				if (args[2].equals("TEA_LEAF")) {
					return giveItem(TeaLeaf.getTeaLeaf(), target, sender);
				}
			}
			// Logic here for specified amount
			// else if (args.length == 4) {
		}
		return false;
	}

	private boolean giveItem(ItemStack itemToAdd, Player target, CommandSender sender) {
		if (target != null) {
			if (Utils.hasInventorySpace(target)) {
				target.getInventory().addItem(itemToAdd);
				return (isSenderAnotherPlayer(itemToAdd, target, sender));
			}
			
			ListIterator<ItemStack> iterator = target.getInventory().iterator();
			// Checks if the ItemStack already is in the inventory
			while (iterator.hasNext()) {
				int iteratedIndex = iterator.nextIndex();
				ItemStack is = iterator.next();
				
				// If it's the same item
				if (is != null && is.getType() == itemToAdd.getType() && Utils.isMatchingItemStack(is, itemToAdd)) {
					// If the ItemStack isn't full
					if (is.getAmount() < is.getMaxStackSize()) {
						is.setAmount(is.getAmount() + 1);
						target.getInventory().setItem(iteratedIndex, is);
						return isSenderAnotherPlayer(itemToAdd, target, sender);
					}
				}
			}

		} else {
			sender.sendMessage(Utils.chatMessage("&cThat player is not online!"));
		}
		return false;
	}
	
	private boolean isSenderAnotherPlayer(ItemStack itemToAdd, Player target, CommandSender sender) {
		if (sender instanceof Player) {
			Player commandSender = (Player) sender;
			target.sendMessage(Utils.chatMessage("&cYou have been given 1 &e" + itemToAdd.getItemMeta().getDisplayName() + "&c!"));
			if (!commandSender.equals(target)) {
				commandSender.sendMessage(Utils.chatMessage("&e" + target.getDisplayName() +
						" &chas been given 1 " + itemToAdd.getItemMeta().getDisplayName()));
			}
		}
		return true;
	}

}