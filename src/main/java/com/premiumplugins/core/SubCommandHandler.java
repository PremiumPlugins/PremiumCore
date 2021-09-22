package com.premiumplugins.core;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SubCommandHandler implements CommandExecutor, ISubCommandHandler {

    protected List<SubCommand> subcommands;

    @Getter
    protected String[] arguments;
    protected CommandSender sender;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] arguments) {
        this.arguments = arguments;
        this.sender = sender;

        if (arguments.length == 0) noArguments().run(); else {

            for (SubCommand subcommand : getSubCommands()) {
                if (arguments[0].equals(subcommand.name())) {
                    if (sender.hasPermission(subcommand.permission())) {
                        String[] args;

                        if (arguments.length > 1) {
                            List<String> ls = new ArrayList<>(Arrays.asList(arguments));
                            ls.remove(0);
                            String str = "";
                            for (String s : ls) {
                                str = str.concat(s + " ");
                            }
                            str = str.substring(0, str.length()-1);
                            args = str.split("\\s");
                        } else args = new String[0];

                        if (subcommand.requiresPlayer()) {
                            if (sender instanceof Player) subcommand.execute((Player) sender, args); else onlyPlayers().run();
                        } else {
                            subcommand.execute(sender, args);
                        }

                        return false;
                    } else {
                        noPermission().run();
                        return true;
                    }
                }
            }

            subCommandNotFound().run();
            return true;
        }

        return false;
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public List<SubCommand> getSubCommands() {
        return subcommands;
    }

    @Override
    public void setSubCommands(List<SubCommand> subcommands) {
        this.subcommands = subcommands;
    }
}
