package me.jonakls.souppvp.storage;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import me.jonakls.souppvp.storage.database.IConnection;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataStorage {

    private final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private final IConnection connection;
    private final PluginCore pluginCore;
    private final FileManager config;

    public DataStorage(IConnection connection, PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.config = pluginCore.getFilesLoader().getConfig();
        this.connection = connection;
        this.initialize();
    }

    private void initialize() {
        EXECUTOR.submit(() -> {
            try {
                Connection con = connection.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS " + config.getString("database.table")
                                + " (id VARCHAR(36) PRIMARY KEY, name VARCHAR(60), kills INTEGER, deaths INTEGER, xp INTEGER)"
                );
                statement.execute();
                pluginCore.getPlugin().getServer().getLogger().info(Colorized.apply("&aLoading database success!"));

            }catch (SQLException e){
                e.printStackTrace();
            }

        });
    }

    public void insert(PlayerCache cache, Player player) {
        EXECUTOR.submit(() -> {
            try {
                Connection con = connection.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "REPLACE INTO " + config.getString("database.table") + " (id, name, kills, deaths, xp) VALUES (?, ?, ?, ?, ?)"
                );
                statement.setString(1, cache.getId(player).toLowerCase());
                statement.setString(2, player.getName());
                statement.setInt(3, cache.getKills(player));
                statement.setInt(4, cache.getDeaths(player));
                statement.setInt(5, cache.getXp(player));

                statement.execute();

            }catch (SQLException e){
                e.printStackTrace();
            }

        });
    }

    public int getKills(String id) {
        try {
            Connection con = connection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + config.getString("database.table") + " WHERE id = '" + id + "'");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(result.getString("id").equalsIgnoreCase(id.toLowerCase())) {
                    return result.getInt("kills");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDeaths(String id) {
        try {
            Connection con = connection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + config.getString("database.table") + " WHERE id = '" + id + "'");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(result.getString("id").equalsIgnoreCase(id.toLowerCase())) {
                    return result.getInt("deaths");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getXp(String id) {
        try {
            Connection con = connection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + config.getString("database.table") + " WHERE id = '" + id + "'");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(result.getString("id").equalsIgnoreCase(id.toLowerCase())) {
                    return result.getInt("xp");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
