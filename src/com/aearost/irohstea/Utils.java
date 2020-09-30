package com.aearost.irohstea;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Utils {
	
	public final static String PREFIX = Utils.chat("&2[&aIroh's Teas&2] &r");
	
	/**
	 * Allows the formatting of messages to contain Minecraft colors
	 * 
	 * @param msg
	 * @return
	 */
	public static String chat(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
