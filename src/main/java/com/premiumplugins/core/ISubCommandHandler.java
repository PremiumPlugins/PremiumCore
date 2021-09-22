package com.premiumplugins.core;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommandHandler {

    default Runnable onlyPlayers() {
        return () -> sendMessage(Resources.plugin.getPrefix() + ChatColor.RED + "Only players can execute this command!");
    }

    default Runnable noArguments() {
        PremiumPlugin<?> plugin = Resources.plugin;
        return () -> {
            sendMessage(plugin.getPrefix() + "Running " + plugin.getPrimaryColors().get(0) + plugin.getPluginName() + " v" + plugin.getPluginVersion() + ChatColor.WHITE + ".");
            boolean permission = false;
            for (SubCommand cmd : getSubCommands()) { if (getSender().hasPermission(cmd.permission())) permission = true; }
            if (permission) sendMessage(plugin.getPrimaryColors().get(0) + " > " + ChatColor.WHITE + "Type /" + plugin.getPluginName().toLowerCase() + " help for a list of commands.");
            else sendMessage(plugin.getPrimaryColors().get(0) + " > " + ChatColor.WHITE + "You do not have permission to use any commands.");
        };
    }

    default Runnable noPermission() {
        return () -> sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
    }

    default Runnable subCommandNotFound() {
        return () -> sendMessage(Resources.plugin.getPrefix() + ChatColor.RED + "Unknown command. Type /" + Resources.plugin.getPluginName().toLowerCase() + " help for a list of commands.");
    }

    default void addSubCommand(SubCommand command) {
        List<SubCommand> ls = getSubCommands();
        ls.add(command);
        setSubCommands(ls);
    }

    default void removeSubCommand(SubCommand command) {
        List<SubCommand> ls = getSubCommands();
        ls.remove(command);
        setSubCommands(ls);
    }

    void sendMessage(String message);
    CommandSender getSender();

    List<SubCommand> getSubCommands();
    void setSubCommands(List<SubCommand> subcommands);

}
