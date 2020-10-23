package com.aearost.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
	
	/**
	 * Allows the formatting of messages to contain Minecraft colors
	 * 
	 * @param msg
	 * @return
	 */
	public static String chatMessage(String msg) {
		return ChatColor.translateAlternateColorCodes('&', "&2[&aIroh's Teas&2] &r" + msg);
	}

	/**
	 * Allows the formatting of messages to contain Minecraft colors
	 * 
	 * @param msg
	 * @return
	 */
	public static String translateToColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static String stripColor(String msg) {
		String colorStripped = ChatColor.stripColor(msg);
		while (colorStripped.startsWith("&")) {
			colorStripped = colorStripped.substring(2);
		}
		return colorStripped;
	}
	
}
