package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.enums.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.MetadataValue;

public class BlockBreakListener implements Listener {

    private final PluginCore pluginCore;

    public BlockBreakListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onBreakEdit(BlockBreakEvent event) {
        if (event.getPlayer().hasMetadata("status")) {
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

}
