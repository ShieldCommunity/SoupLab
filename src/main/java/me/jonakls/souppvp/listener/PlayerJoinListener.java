package me.jonakls.souppvp.listener;


import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.enums.StatusGame;
import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerJoinListener implements Listener {

    private final PluginCore pluginCore;

    public PlayerJoinListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        FilesLoader file = pluginCore.getFilesLoader();
        event.getPlayer().setMetadata("status", new FixedMetadataValue(pluginCore.getPlugin(), StatusGame.SPAWN));
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
