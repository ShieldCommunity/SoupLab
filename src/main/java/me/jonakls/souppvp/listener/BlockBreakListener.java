package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final PluginCore pluginCore;

    public BlockBreakListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(pluginCore.getFilesLoader().getConfig().getBoolean("deny-build"));
    }

}
