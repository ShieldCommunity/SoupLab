package me.jonakls.souppvp.storage;

import me.jonakls.souppvp.storage.database.IConnection;
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

    public DataStorage(IConnection connection) {
        this.connection = connection;
        this.initialize();
    }

    private void initialize() {
        EXECUTOR.submit(() -> {
            try {
                Connection con = connection.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS soup(id VARCHAR(36) PRIMARY KEY, name VARCHAR(60), kills INTEGER, deaths INTEGER)"
                );
                statement.execute();

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
                        "REPLACE INTO soup(id, name, kills, deaths) VALUES (?, ?, ?, ?)"
                );
                statement.setString(1, cache.getId(player));
                statement.setString(2, player.getName());
                statement.setString(3, "" + cache.getKills(player));
                statement.setString(4, "" + cache.getDeaths(player));

                statement.execute();

            }catch (SQLException e){
                e.printStackTrace();
            }

        });
    }

    public int getKills(String id) {
        EXECUTOR.submit(() -> {
            try {
                Connection con = connection.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "SELECT * FROM soup WHERE id = " + id
                );
                statement.executeQuery();

                ResultSet result = statement.getResultSet();

                if(result.getString("id").equals(id)) {
                    return Integer.parseInt(result.getString("kills"));
                }
                return 0;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
        return 0;
    }

}
