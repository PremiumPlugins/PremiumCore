package com.premiumplugins.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

@Data
@RequiredArgsConstructor
@SuppressWarnings({"ConstantConditions", "unused", "UnnecessaryEnumModifier"})
public abstract class Database {

    private final String database;
    private final String host;
    private final String port;

    private final String username;
    private final String password;

    private Connection connection;

    public Database createConnection(@Nullable Runnable... err) {
        try {
            DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        } catch (SQLException e) {
            handleException(e, err);
        }
        return this;
    }

    public ResultSet query(@NotNull String sql, @Nullable Runnable... err) {
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            handleException(e, err);
        }
        return null;
    }

    public void execute(@NotNull String sql, @Nullable Runnable... err) {
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            handleException(e, err);
        }
    }

    public DatabaseMetaData getMetaData(@Nullable Runnable... err) {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            handleException(e, err);
        }
        return null;
    }

    public TableResult createTable(@NotNull String name, @NotNull String sql, @Nullable Runnable... err) {
        try {
            DatabaseMetaData meta = getMetaData();
            ResultSet set = meta.getTables(null, null, name, null);
            if (set.next()) return TableResult.ALREADY_EXISTS; else {
                execute(sql);

                return TableResult.SUCCESS;
            }
        } catch (SQLException e) {
            handleException(e, err);
            return TableResult.ERROR;
        }
    }

    public static enum TableResult {
        ALREADY_EXISTS,
        SUCCESS,
        ERROR
    }

    private void handleException(@NotNull SQLException e, @Nullable Runnable... err) {
        if (err == null) return;

        if (err.length == 0) {
            e.printStackTrace();
        } else {
            err[0].run();
        }
    }

}
