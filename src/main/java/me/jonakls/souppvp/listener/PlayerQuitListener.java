package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private PluginCore pluginCore;

    public PlayerQuitListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.getPlayer().setLevel(0);
    }
}
