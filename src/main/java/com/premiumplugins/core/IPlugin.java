package com.premiumplugins.core;

public interface IPlugin {

    default void registerListeners() {

    }
    default void registerCommands() {

    }

    default void loadConfiguration() {

    }

    default void loadMessages() {

    }

    default void loadStorage() {

    }

    default void loadData() {

    }

    default void loadMetrics() {

    }

    void sendStartupMessage();

}
