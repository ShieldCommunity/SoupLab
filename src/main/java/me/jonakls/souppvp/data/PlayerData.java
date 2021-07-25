package me.jonakls.souppvp.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerData {

    private final List<String> players;
    private final Map<String, Integer> kills;
    private final Map<String, Integer> deaths;
    private final Map<String, Integer> xp;

    public PlayerData() {
        this.kills = new HashMap<String, Integer>(){};
        this.deaths = new HashMap<String, Integer>(){};
        this.xp = new HashMap<String, Integer>(){};
        this.players = new ArrayList<>();
    }

    public void registerPlayer(Player player) {
        if (players.contains(player.getName())) return;
        players.add(player.getName());
    }

    public void incrementKills(Player player) {
        Integer killsValue = this.kills.get(player.getUniqueId().toString());
        if(killsValue == null) {
            this.kills.put(player.getUniqueId().toString(), 0);
            killsValue = 0;
        }
        this.kills.put(player.getUniqueId().toString(), killsValue + 1 );
    }

    public void incrementDeaths(Player player) {
        Integer deathsValue = this.deaths.get(player.getUniqueId().toString());
        if(deathsValue == null) {
            this.deaths.put(player.getUniqueId().toString(), 0);
            deathsValue = 0;
        }
        this.deaths.put(player.getUniqueId().toString(), deathsValue + 1 );
    }

    public void incrementXp(Player player) {
        Integer xpValue = this.xp.get(player.getUniqueId().toString());
        if(xpValue == null) {
            this.xp.put(player.getUniqueId().toString(), 0);
            xpValue = 0;
        }
        this.xp.put(player.getUniqueId().toString(), xpValue + 1 );
    }

    public int getKills(Player player) {
        Integer killsValue = this.kills.get(player.getUniqueId().toString());
        if(killsValue == null) {
            this.kills.put(player.getUniqueId().toString(), 0);
            killsValue = 0;
        }
        return killsValue;
    }

    public int getDeaths(Player player) {
        Integer deathsValue = this.deaths.get(player.getUniqueId().toString());
        if(deathsValue == null) {
            this.deaths.put(player.getUniqueId().toString(), 0);
            deathsValue = 0;
        }
        return deathsValue;
    }

    public int getXp(Player player) {
        Integer xpValue = this.xp.get(player.getUniqueId().toString());
        if(xpValue == null) {
            this.xp.put(player.getUniqueId().toString(), 0);
            xpValue = 0;
        }
        return xpValue;
    }

}
