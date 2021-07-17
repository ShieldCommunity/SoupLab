package me.jonakls.souppvp.listener;


import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FilesLoader loader;

    public PlayerJoinListener(FilesLoader loader) {
        this.loader = loader;
    }

    @EventHandler
    public void onJoinClearInventory(PlayerJoinEvent event) {
        if (!loader.getConfig().getBoolean("clear-on-join")) return;

        event.getPlayer().getInventory().clear();
        event.getPlayer().getInventory().setHelmet(null);
        event.getPlayer().getInventory().setChestplate(null);
        event.getPlayer().getInventory().setLeggings(null);
        event.getPlayer().getInventory().setBoots(null);
    }

    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        if (!loader.getConfig().getBoolean("items-on-join")) return;

        ItemBuilder builder = new ItemBuilder(
                Material.valueOf(loader.getConfig().getString("items-join.kits.material")),
                1,
                loader.getConfig().getString("items-join.kits.display"),
                loader.getConfig().getStringList("items-join.kits.lore")
        );

        event.getPlayer().getInventory().setItem(
                loader.getConfig().getInt("items-join.kits.slot"),
                builder.getItem()
        );
    }





}
