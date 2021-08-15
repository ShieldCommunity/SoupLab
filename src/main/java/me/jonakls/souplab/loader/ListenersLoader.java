package me.jonakls.souplab.loader;

import me.jonakls.souplab.PluginCore;
import me.jonakls.souplab.SoupLab;
import me.jonakls.souplab.api.Loader;
import me.jonakls.souplab.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;


public class ListenersLoader implements Loader {

    private final PluginCore pluginCore;

    public ListenersLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerListeners(
                new PlayerJoinListener(pluginCore),
                new PlayerInteractListener(pluginCore),
                new PlayerDropItemListener(pluginCore.getFilesLoader()),
                new InventoryClickListener(pluginCore),
                new FoodChangeListener(pluginCore.getFilesLoader()),
                new EntityDamageListener(pluginCore.getFilesLoader()),
                new SignChangeListener(pluginCore.getFilesLoader()),
                new BlockPlaceListener(pluginCore),
                new PlayerDeathListener(pluginCore),
                new PlayerPickupListener(pluginCore),
                new BlockBreakListener(pluginCore),
                new PlayerQuitListener(pluginCore)
        );
    }

    public void registerListeners(Listener... listeners){
        PluginManager pluginManager = Bukkit.getPluginManager();
        SoupLab plugin = pluginCore.getPlugin();

        for (Listener listener : listeners){
            pluginManager.registerEvents(listener, plugin);
        }
    }
}
