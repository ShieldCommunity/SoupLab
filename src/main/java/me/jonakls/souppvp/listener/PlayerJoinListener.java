package me.jonakls.souppvp.listener;


import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FilesLoader file;

    public PlayerJoinListener(FilesLoader file) {
        this.file = file;
    }

    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        if (!file.getConfig().getBoolean("items-on-join")) return;

        if (file.getConfig().getBoolean("clear-on-join")){
            event.getPlayer().getInventory().clear();
            event.getPlayer().getInventory().setHelmet(null);
            event.getPlayer().getInventory().setChestplate(null);
            event.getPlayer().getInventory().setLeggings(null);
            event.getPlayer().getInventory().setBoots(null);

        }

        ItemBuilder builder = new ItemBuilder(
                Material.valueOf(file.getConfig().getString("items-join.kits.material")),
                1,
                file.getConfig().getString("items-join.kits.display"),
                file.getConfig().getStringList("items-join.kits.lore")
        );

        event.getPlayer().getInventory().setItem(
                file.getConfig().getInt("items-join.kits.slot"),
                builder.getItem()
        );
    }





}
