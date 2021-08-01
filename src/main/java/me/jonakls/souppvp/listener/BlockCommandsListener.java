package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCommandsListener implements Listener {

    private final FileManager lang;
    private final FileManager config;

    public BlockCommandsListener(PluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.lang = pluginCore.getFilesLoader().getLang();
    }

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent event) {
        if (!(config.getBoolean("commands.enable"))) return;
        Bukkit.broadcastMessage(event.getMessage());

        for(String command : config.getStringList("commands.allow-commands")) {

            if(!command.equalsIgnoreCase(event.getMessage())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(lang.getString("prefix") + lang.getString("error.no-command"));
                return;
            }
        }
    }
}