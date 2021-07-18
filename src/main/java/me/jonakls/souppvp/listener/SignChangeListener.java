package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private final FilesLoader files;

    public SignChangeListener(FilesLoader files) {
        this.files = files;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getBlock() == null) return;
        if (!event.getBlock().getType().equals(Material.WALL_SIGN)) return;

        String[] lines = files.getConfig().getString("signs.soups").split(";");

        if (event.getLine(0).equalsIgnoreCase("[soup]")) {

            for (int i = 0 ; i < lines.length ; i++) {

                event.setLine(
                        i,
                        Colorized.apply(lines[i])
                );

            }
            return;
        }
        event.setCancelled(true);
    }


}
