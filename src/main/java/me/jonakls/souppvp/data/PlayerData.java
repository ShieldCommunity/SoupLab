package me.jonakls.souppvp.data;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    private final PluginCore pluginCore;

    private final List<Player> players;
    private final Map<String, Integer> kills;
    private final Map<String, Integer> deaths;
    private final Map<String, Integer> xp;

    public PlayerData(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.kills = new HashMap<String, Integer>(){};
        this.deaths = new HashMap<String, Integer>(){};
        this.xp = new HashMap<String, Integer>(){};
        this.players = new ArrayList<>();
    }

    public void savePlayerData() {
        FileManager data = pluginCore.getFilesLoader().getData();

        for (Player player : players) {
            data.set("data." + player.getName() + ".uuid", player.getUniqueId().toString());
            data.set("data." + player.getName() + ".kills", this.getKills(player));
            data.set("data." + player.getName() + ".deaths", this.getDeaths(player));
            data.set("data." + player.getName() + ".xp", this.getXp(player));
            data.save();
        }
    }

    public void loadPlayerData(Player player) {
        FileManager data = pluginCore.getFilesLoader().getData();
        Set<String> paths = data.getConfigurationSection("data").getKeys(false);

        if (players.contains(player)) return;
        this.registerPlayer(player);

        for (String name : paths) {

            if(name.equals(player.getName())) {
                this.kills.put(name, data.getInt("data." + name + ".kills"));
                this.deaths.put(name, data.getInt("data." + name + ".deaths"));
                this.xp.put(name, data.getInt("data." + name + ".xp"));
            }
        }
    }

    public void registerPlayer(Player player) {
        if (players.contains(player)) return;
        players.add(player);
    }

    public void incrementKills(Player player) {
        Integer killsValue = this.kills.get(player.getName());
        if(killsValue == null) {
            this.kills.put(player.getName(), 0);
            killsValue = 0;
        }
        this.kills.put(player.getName(), killsValue + 1 );
    }

    public void incrementDeaths(Player player) {
        Integer deathsValue = this.deaths.get(player.getName());
        if(deathsValue == null) {
            this.deaths.put(player.getName(), 0);
            deathsValue = 0;
        }
        this.deaths.put(player.getName(), deathsValue + 1 );
    }

    public void incrementXp(Player player) {
        Integer xpValue = this.xp.get(player.getName());
        if(xpValue == null) {
            this.xp.put(player.getName(), 0);
            xpValue = 0;
        }
        this.xp.put(player.getName(), xpValue + 1 );
    }

    public int getKills(Player player) {
        Integer killsValue = this.kills.get(player.getName());
        if(killsValue == null) {
            this.kills.put(player.getName(), 0);
            killsValue = 0;
        }
        return killsValue;
    }

    public int getDeaths(Player player) {
        Integer deathsValue = this.deaths.get(player.getName());
        if(deathsValue == null) {
            this.deaths.put(player.getName(), 0);
            deathsValue = 0;
        }
        return deathsValue;
    }

    public int getXp(Player player) {
        Integer xpValue = this.xp.get(player.getName());
        if(xpValue == null) {
            this.xp.put(player.getName(), 0);
            xpValue = 0;
        }
        return xpValue;
    }

}
