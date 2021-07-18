package me.jonakls.souppvp.handlers;

import me.jonakls.souppvp.builders.ItemBuilder;
import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class KitHandler {

    private final FilesLoader file;

    public KitHandler(FilesLoader loader) {
        this.file = loader;
    }

    public void setPlayerKit(Player player, String kitName) {


        PlayerInventory inventory = player.getInventory();

        inventory.clear();

        for (String path : file.getKits().getConfigurationSection("kits").getKeys(false)) {

            if (path.toLowerCase().equals(kitName)) {

                player.sendMessage(Colorized.apply("&aYou selected &c" + kitName));

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

                    ItemBuilder builder = new ItemBuilder(Material.valueOf(strings[0]), Integer.parseInt(strings[1]));

                    inventory.setItem(i, builder.getItem());

                }
            }

        }
    }



}
