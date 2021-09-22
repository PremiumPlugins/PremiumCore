package com.premiumplugins.core;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public interface SubCommand {

    default String name() {
        return getClass().getName().split("\\.")[getClass().getName().split("\\.").length-1].toLowerCase().replaceFirst("command", "");
    }

    default String permission() {
        if (!getClass().isAnnotationPresent(Permission.class)) return Resources.plugin.getPluginName() + name();
        return Resources.plugin.getPluginName() + getClass().getDeclaredAnnotation(Permission.class).permission();
    }

    default String usage() {
        if (!getClass().isAnnotationPresent(Usage.class)) return "/" + Resources.plugin.getPluginName().toLowerCase() + name();
        String[] usages = getClass().getDeclaredAnnotation(Usage.class).usage();
        String usage = "";
        for (String s : usages) {
            usage = usage.concat(s + " || ");
        }
        usage = usage.substring(0, usage.length()-4);
        return usage;
    }

    default String description() {
        if (!getClass().isAnnotationPresent(Description.class)) return "";
        return getClass().getDeclaredAnnotation(Description.class).description();
    }

    default List<String> tab() {
        if (!getClass().isAnnotationPresent(TabCompletion.class)) return null;
        List<String> ls = new ArrayList<>();
        Collections.addAll(ls, getClass().getDeclaredAnnotation(TabCompletion.class).tabs());
        return ls;
    }

    default boolean requiresPlayer() {
        if (!getClass().isAnnotationPresent(RequiresPlayer.class)) return false;
        return getClass().getDeclaredAnnotation(RequiresPlayer.class).requiresPlayer();
    }

    default void execute(Player player, String[] args) {

    }

    default void execute(CommandSender sender, String[] args) {

    }

}
