package me.jonakls.souplab;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoupLab extends JavaPlugin {

    private final PluginCore core = new PluginCore(this);

    @Override
    public void onEnable() {

        getLogger().info("Loading SoupPvP v" + getDescription().getVersion());
        this.core.init();

        getServer().getScheduler().runTaskTimer(this,() -> {

            for(FastBoard board : core.gameScoreboard().getBoards().values()) {

                core.gameScoreboard().update(board);
            }

        }, 0 , 20L);

    }

    @Override
    public void onDisable() {
        getLogger().info("Closing SoupPvP v" + getDescription().getVersion());
        getLogger().info("Saving player data...");
        this.core.getPlayerCache().forceSaveData();
        this.core.closeDatabase();
        getLogger().info("Closed connection, finally!");
    }
}
