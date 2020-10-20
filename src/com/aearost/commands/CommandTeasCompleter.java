package com.aearost.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.aearost.items.Items;

public class CommandTeasCompleter implements TabCompleter {

	/**
	 * Handles the auto complete functionality while using the /teas command, and
	 * all of its sub-commands.
	 */
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> displayedOptions = new ArrayList<>();

		if ("book".startsWith(args[0]) && args[0].length() > 0) {
			if (args.length == 1) {
				displayedOptions.add("book");
			}
		} else if ("give".startsWith(args[0]) && args[0].length() > 0) {
			if (args.length == 1) {
				displayedOptions.add("give");
			}
			// List online players
			else if (args.length == 2) {
				Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
				Bukkit.getOnlinePlayers().toArray(onlinePlayers);
				for (int i = 0; i < onlinePlayers.length; i++) {
					if (onlinePlayers[i].getName().toLowerCase().startsWith(args[1].toLowerCase())) {
						displayedOptions.add(onlinePlayers[i].getName());
					}
				}
			}
			// If the input player exists
			else if (args.length == 3 && args[0].equals("give")
					&& Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
				for (Items i : Items.values()) {
					if (i.name().toLowerCase().startsWith(args[2].toLowerCase())) {
						displayedOptions.add(i.name());
					}
				}
			}
			// Prompts amount but clears once something is entered
			else if (args.length == 4) {
				if (args[3].equals("")) {
					displayedOptions.add("1");
					displayedOptions.add("64");
				}
			}

		} else if ("kettles".startsWith(args[0]) && args[0].length() > 0) {
			if (args.length == 1) {
				displayedOptions.add("kettles");
			} else if (args.length == 2) {
				if ("display".startsWith(args[1]) && args[1].length() > 0) {
					displayedOptions.add("display");
				} else if ("remove".startsWith(args[1]) && args[1].length() > 0) {
					displayedOptions.add("remove");
					displayedOptions.add("removeall");
				} else if ("removeall".startsWith(args[1]) && args[1].length() > 0) {
					displayedOptions.add("removeall");
				} else {
					displayedOptions.add("display");
					displayedOptions.add("remove");
					displayedOptions.add("removeall");
				}
			} else if (args.length == 3 && args[1].equals("remove")) {
				displayedOptions.add("0");
			} else if (args.length == 4 && args[1].equals("remove")) {
				displayedOptions.add("0");
			} else if (args.length == 5 && args[1].equals("remove")) {
				displayedOptions.add("0");
			}
		} else if (args.length == 1) {
			displayedOptions.add("book");
			displayedOptions.add("give");
			displayedOptions.add("kettles");
		}

		return displayedOptions;
	}

}
