package com.premiumplugins.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class PremiumPlugin<P extends PremiumPlugin<?>> extends JavaPlugin implements IPlugin {

    public void setPlugin(P plugin) {
        Resources.setPlugin(plugin);
    }

    public void setDatabase(Database database) {
        Resources.setDatabase(database);
    }

    public static PremiumPlugin<?> getPlugin() {
        return Resources.plugin;
    }

    public Database getDatabase() {
        return Resources.database;
    }

    public String getPluginName() {
        return getDescription().getName();
    }

    public String getPluginVersion() {
        return getDescription().getVersion();
    }

    public String getPluginDescription() {
        return getDescription().getDescription();
    }

    public void reload() {
        onDisable();
        onEnable();
    }

    abstract List<ChatColor> getPrimaryColors();

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.colorize(message));
    }

    abstract String getPrefix();

}
