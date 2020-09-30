package com.aearost.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aearost.irohstea.Utils;

public class CommandTeas implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(Utils.chat(Utils.PREFIX + "&d&l&o/teas &5&l&ois working!"));
		}
		
		return true;
	}

}