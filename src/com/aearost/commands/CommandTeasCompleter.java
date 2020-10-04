package com.aearost.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

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
		// If the inputted player exists
		else if (args.length == 3 && args[0].equals("give") && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
			displayedOptions.add("TEA_LEAF");
		}
		return displayedOptions;
	}
	
}
