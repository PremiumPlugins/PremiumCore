package com.premiumplugins.core;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String colorize(char characterToTranslate, String text) {
        return ChatColor.translateAlternateColorCodes(characterToTranslate, text);
    }

}
