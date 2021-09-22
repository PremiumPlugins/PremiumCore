package com.premiumplugins.core;

public class Resources {

    public static PremiumPlugin<?> plugin;
    public static Database database;

    public static void setPlugin(PremiumPlugin<?> plugin) {
        Resources.plugin = plugin;
    }

    public static void setDatabase(Database database) {
        Resources.database = database;
    }
}
