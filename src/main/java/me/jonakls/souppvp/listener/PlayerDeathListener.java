package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.enums.StatusGame;
import me.jonakls.souppvp.loader.FilesLoader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerDeathListener implements Listener {

    private final PluginCore pluginCore;

    public PlayerDeathListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        FilesLoader file = pluginCore.getFilesLoader();
        event.getEntity().setMetadata("status", new FixedMetadataValue(pluginCore.getPlugin(), StatusGame.SPAWN));
        event.getEntity().spigot().respawn();
        event.setKeepInventory(true);
        event.getEntity().getInventory().clear();
        event.getEntity().getInventory().setBoots(null);
        event.getEntity().getInventory().setLeggings(null);
        event.getEntity().getInventory().setChestplate(null);
        event.getEntity().getInventory().setHelmet(null);
        event.setNewLevel(0);
        event.setDroppedExp(0);

        ItemBuilder builder = new ItemBuilder(
                Material.valueOf(file.getConfig().getString("items-join.kits.material")),
                1,
                file.getConfig().getString("items-join.kits.display"),
                file.getConfig().getStringList("items-join.kits.lore")
        );

        event.getEntity().getInventory().setItem(
                file.getConfig().getInt("items-join.kits.slot"),
                builder.getItem()
        );

    }


}
