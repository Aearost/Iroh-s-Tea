package com.aearost.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.aearost.irohstea.Items;
import com.aearost.irohstea.Utils;
import com.aearost.items.TeaLeaf;

public class CommandTeas implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Utils.translateToColor("&a         - - &2&lIroh's Teas &a- -"));
			sender.sendMessage(Utils.translateToColor("&6/teas &egive <player> <item> &7[amount]"));
			return false;
		} else if (args.length < 3) {
			sender.sendMessage(Utils.translateToColor("&aProper Usage: &6/teas &egive <player> <item> &7[amount]"));
			return false;
		}

		if (args.length >= 3 && args[0].equals("give")) {
			// Creates a list of all Items
			List<String> itemsAsList = new ArrayList<>();
			for (Items i : Items.values()) {
				itemsAsList.add(i.name());
			}

			if (!itemsAsList.contains(args[2])) {
				sender.sendMessage(Utils.chatMessage("&7" + args[2] + " &cdoes not exist!"));
				return false;
			}

			Player target = Bukkit.getPlayer(args[1]);
			if (args.length == 4) {
				int amount;
				try {
					amount = Integer.parseInt(args[3]);
				} catch (NumberFormatException e) {
					sender.sendMessage(Utils.chatMessage("&cThat is not a valid amount!"));
					return false;
				}
				// In valid inventory slot range
				if (amount > 0 && amount <= Utils.MAXIMUM_ITEM_AMOUNT) {
					ItemStack is = Utils.getItem(args[2]);
					is.setAmount(amount);
					return giveItem(is, target, sender);
				} else {
					sender.sendMessage(Utils.chatMessage("&cThat is not a valid amount!"));
				}
			} else {
				ItemStack is = Utils.getItem(args[2]);
				// If not specified, will give 64
				is.setAmount(64);
				return giveItem(is, target, sender);
			}
		}
		return false;
	}

	/**
	 * Handles giving the target an item.
	 * 
	 * @param itemToAdd
	 * @param target
	 * @param sender
	 * @return
	 */
	private boolean giveItem(ItemStack itemToAdd, Player target, CommandSender sender) {
		if (target != null) {
			ItemStack copyForHasSpace = itemToAdd.clone();
			int remainder = Utils.addToInventory(target, copyForHasSpace);
			if (remainder == 0) {
				return sendMessages(itemToAdd, target, sender, 0);
			} else if (remainder == -1) {
				return sendMessages(itemToAdd, target, sender, -1);
			} else {
				return sendMessages(itemToAdd, target, sender, remainder);
			}
		} else {
			sender.sendMessage(Utils.chatMessage("&cThat player is not online!"));
		}
		return false;
	}

	/**
	 * Sends the respective messages to the respective location based on the
	 * remainingAmount.
	 * 
	 * @param itemToGive
	 * @param target
	 * @param sender
	 * @param remainingAmount The remaining amount of Utils.addToInventory().
	 * @return Whether to successfully execute the command or not.
	 */
	private boolean sendMessages(ItemStack itemToGive, Player target, CommandSender sender, int remainingAmount) {
		// If the the sender gave themselves the item
		if (sender instanceof Player) {
			Player senderAsPlayer = (Player) sender;
			String itemName = itemToGive.getItemMeta().getDisplayName();

			// If the sender is also the target
			if (senderAsPlayer.getName().equals(target.getName())) {
				if (remainingAmount == 0) {
					target.sendMessage(Utils
							.chatMessage("&6You have been given &a" + itemToGive.getAmount() + " " + itemName + "&6!"));
					return true;
				} else if (remainingAmount > 0) {
					int amountGiven = itemToGive.getAmount() - remainingAmount;
					target.sendMessage(
							Utils.chatMessage("&6You have been given &a" + amountGiven + " " + itemName + "&6!"));
					target.sendMessage(
							Utils.chatMessage("&a" + remainingAmount + " " + itemName + " &6was thrown away!"));
					return true;
				} else {
					target.sendMessage(Utils.chatMessage("&cYou do not have enough space for that!"));
					return false;
				}
			}
		}

		// If someone else (including console) gave them the item
		String itemName = itemToGive.getItemMeta().getDisplayName();
		if (remainingAmount == 0) {
			target.sendMessage(
					Utils.chatMessage("&6You have been given &a" + itemToGive.getAmount() + " " + itemName + "&6!"));
			sender.sendMessage(Utils.chatMessage("&e" + target.getName() + " &6has been given &a"
					+ itemToGive.getAmount() + " " + itemName + "&6!"));
			return true;
		} else if (remainingAmount > 0) {
			int amountGiven = itemToGive.getAmount() - remainingAmount;
			target.sendMessage(Utils.chatMessage("&6You have been given &a" + amountGiven + " " + itemName + "&6!"));
			sender.sendMessage(Utils.chatMessage(
					"&e" + target.getName() + " &6has been given &a" + amountGiven + " " + itemName + "&6!"));
			sender.sendMessage(Utils.chatMessage("&a" + remainingAmount + " " + itemName + " &6was thrown away!"));
			return true;
		} else {
			sender.sendMessage(Utils.chatMessage("&7" + target.getName() + " &cdoes not have enough space for that!"));
			return false;
		}
	}
}