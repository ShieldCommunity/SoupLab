package me.jonakls.souppvp;

import org.bukkit.plugin.java.JavaPlugin;

public final class SoupPvP extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCore core = new PluginCore(this);
        core.init();

    }
}
