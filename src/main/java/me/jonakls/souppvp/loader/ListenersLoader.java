package me.jonakls.souppvp.loader;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.SoupPvP;
import me.jonakls.souppvp.api.Loader;
import me.jonakls.souppvp.listener.*;
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
                new PlayerQuitListener(pluginCore),
                new BlockCommandsListener(pluginCore)
        );
    }

    public void registerListeners(Listener... listeners){
        PluginManager pluginManager = Bukkit.getPluginManager();
        SoupPvP plugin = pluginCore.getPlugin();

        for (Listener listener : listeners){
            pluginManager.registerEvents(listener, plugin);
        }
    }
}
