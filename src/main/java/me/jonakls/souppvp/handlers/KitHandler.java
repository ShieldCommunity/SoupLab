package me.jonakls.souppvp.handlers;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.builders.TitleBuilder;
import me.jonakls.souppvp.enums.StatusGame;
import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class KitHandler {

    private final PluginCore pluginCore;

    public KitHandler(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    public void setPlayerKit(Player player, String kitName) {
        FilesLoader file = pluginCore.getFilesLoader();
        String prefix = file.getLang().getString("prefix");
        if (!player.hasMetadata("status")) return;
        for (MetadataValue meta : player.getMetadata("status")) {

            if (!meta.value().equals(StatusGame.SPAWN)) {
                player.sendMessage(prefix + file.getLang().getString("error.already-kit"));
                return;
            }

            PlayerInventory inventory = player.getInventory();

            inventory.clear();
            inventory.setHelmet(null);
            inventory.setChestplate(null);
            inventory.setLeggings(null);
            inventory.setBoots(null);

            TitleBuilder builder = new TitleBuilder(
                    file.getLang().getString("titles.select-kit.title").replace("%kit%", kitName),
                    file.getLang().getString("titles.select-kit.sub-title").replace("%kit%", kitName)
            );

            builder.setTime(
                    file.getLang().getInt("titles.select-kit.fade-in"),
                    file.getLang().getInt("titles.select-kit.stay"),
                    file.getLang().getInt("titles.select-kit.fade-out")
            );

            builder.send(player);

            player.sendMessage(prefix + file.getLang().getString("messages.select-kit").replace("%kit%", kitName));

            player.setMetadata("status", new FixedMetadataValue(pluginCore.getPlugin(), StatusGame.IN_GAME));

            for (String path : file.getKits().getConfigurationSection("kits").getKeys(false)) {

                if (path.toLowerCase().equals(kitName)) {

                    inventory.setHelmet(new ItemStack(
                            Material.valueOf(file.getKits().getString("kits." + path + ".armor.head"))
                    ));

                    inventory.setChestplate(new ItemStack(
                            Material.valueOf(file.getKits().getString("kits." + path + ".armor.body"))
                    ));

                    inventory.setLeggings(new ItemStack(
                            Material.valueOf(file.getKits().getString("kits." + path + ".armor.leggins"))
                    ));

                    inventory.setBoots(new ItemStack(
                            Material.valueOf(file.getKits().getString("kits." + path + ".armor.boats"))
                    ));

                    List<String> items = file.getKits().getStringList("kits." + path + ".items");

                    for (int i = 0 ; i < items.size() ; i++) {

                        String[] strings = items.get(i).split(";");

                        ItemBuilder itemBuilder = new ItemBuilder(Material.valueOf(strings[0]), Integer.parseInt(strings[1]));

                        inventory.setItem(i, itemBuilder.getItem());

                    }
                }

            }
        }
    }



}
