package me.jonakls.souplab.listener;

import me.jonakls.souplab.PluginCore;
import me.jonakls.souplab.enums.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.MetadataValue;

public class BlockPlaceListener implements Listener {

    private final PluginCore pluginCore;

    public BlockPlaceListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onPlaceEdit(BlockPlaceEvent event) {
        for (MetadataValue value : event.getPlayer().getMetadata("status")) {
            if (value.value().equals(GameStatus.EDIT_MODE)) {
                event.setCancelled(false);
                return;
            }
            event.setCancelled(pluginCore.getFilesLoader().getConfig().getBoolean("deny-build"));
            return;
        }
    }

}
