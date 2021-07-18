package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final FilesLoader files;

    public BlockPlaceListener(FilesLoader files) {
        this.files = files;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(files.getConfig().getBoolean("deny-build"));
    }

}
