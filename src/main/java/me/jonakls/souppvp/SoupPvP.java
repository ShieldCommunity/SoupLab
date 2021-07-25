package me.jonakls.souppvp;

import org.bukkit.plugin.java.JavaPlugin;

public final class SoupPvP extends JavaPlugin {

    private final PluginCore core = new PluginCore(this);

    @Override
    public void onEnable() {
        this.core.init();

    }

    @Override
    public void onDisable() {
        this.core.getPlayerData().savePlayerData();
    }
}
