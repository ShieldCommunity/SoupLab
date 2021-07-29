package me.jonakls.souppvp.storage.database.types.sqlite;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.storage.database.IConnection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection implements IConnection {

    private final File file;
    private Connection connection;

    public SQLConnection(PluginCore core) {
        this.file = new File(core.getPlugin().getDataFolder(), core.getPlugin().getName() + ".db");

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void load() {
        try {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {

        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + file);
            }
            return connection;
        }catch (SQLException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
