package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.handlers.KitHandler;
import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final FilesLoader files;

    public InventoryClickListener(FilesLoader files) {
        this.files = files;
    }

    @EventHandler
    public void onClickKits(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(files.getGui().getString("kits.title"))) return;
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;
        event.setCancelled(true);

        KitHandler handler = new KitHandler(files);
        for (String path : files.getKits().getConfigurationSection("kits").getKeys(false)) {
            if (!event.getCurrentItem().getItemMeta().getDisplayName()
                    .equals(files.getKits().getString("kits." + path + ".icon.display"))) return;

            handler.setPlayerKit((Player) event.getWhoClicked(), path);

        }
    }

}
