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
		
		if (args.length == 1) {
			displayedOptions.add("give");
			
			return displayedOptions;
		}
		else if (args.length == 2) {
			displayedOptions.add("TEA_LEAF");

			return displayedOptions;
		}
		else if (args.length == 3) {
			Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().size()];
			Bukkit.getOnlinePlayers().toArray(onlinePlayers);
			for (int i = 0; i < onlinePlayers.length; i++) {
				displayedOptions.add(onlinePlayers[i].getName());
			}
			return displayedOptions;
		}
		return null;
	}
	
}
