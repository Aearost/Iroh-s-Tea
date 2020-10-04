package com.aearost.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.aearost.irohstea.Items;

public class CommandTeasCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> displayedOptions = new ArrayList<>();
		
		// May have an error here if displayedOptions is not null since it gets initialized
		// /teas give Aearost TEA_LEAF 7
		
		if (args.length == 1) {
			displayedOptions.add("give");
		}
		else if (args.length == 2 && args[0].equals("give")) {
			Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
			Bukkit.getOnlinePlayers().toArray(onlinePlayers);
			for (int i = 0; i < onlinePlayers.length; i++) {
				if (onlinePlayers[i].getName().toLowerCase().startsWith(args[1].toLowerCase())) {
					displayedOptions.add(onlinePlayers[i].getName());
				}
			}
		}
		// If the input player exists
		else if (args.length == 3 && args[0].equals("give") && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
			for (Items i : Items.values()) {
				if (i.name().toLowerCase().startsWith(args[2].toLowerCase())) {
					displayedOptions.add(i.name());
				}
			}
		}
		// Prompts amount but clears once something is entered
		else if (args.length == 4) {
			if (args[3].equals("")) {
				displayedOptions.add("64");
			}
		}
		return displayedOptions;
	}
	
}
