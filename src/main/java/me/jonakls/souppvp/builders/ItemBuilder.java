package me.jonakls.souppvp.builders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, String itemName) {
        this.item = new ItemStack(material, amount);
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(itemName);
        this.item.setItemMeta(meta);
    }

    public ItemBuilder(Material material, int amount, String itemName, List<String> loreItem) {
        this.item = new ItemStack(material, amount);
        ItemMeta meta = this.item.getItemMeta();
        meta.setLore(loreItem);
        meta.setDisplayName(itemName);
        this.item.setItemMeta(meta);
    }

    public ItemStack getItem(){
        return item;
    }
}
