package me.jonakls.souppvp;

import me.jonakls.souppvp.gui.KitsGUI;
import me.jonakls.souppvp.loader.ManagerLoader;
import me.jonakls.souppvp.api.Core;
import me.jonakls.souppvp.api.Loader;
import me.jonakls.souppvp.loader.CommandsLoader;
import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.loader.ListenersLoader;

public class PluginCore implements Core{

    private final SoupPvP plugin;

    private FilesLoader filesLoader;
    private ManagerLoader managerLoader;
    private KitsGUI kitsGUI;

    public PluginCore(SoupPvP plugin){
        this.plugin = plugin;
    }

    @Override
    public void init() {
        filesLoader = new FilesLoader(plugin);
        filesLoader.load();

        kitsGUI = new KitsGUI(filesLoader);

        managerLoader = new ManagerLoader(this);
        managerLoader.load();

        initLoaders(
                new CommandsLoader(this),
                new ListenersLoader(this));
    }


    private void initLoaders(Loader... loaders){
        for (Loader loader : loaders){
            loader.load();
        }
    }


    public FilesLoader getFilesLoader() {
        return filesLoader;
    }

    public SoupPvP getPlugin(){
        return plugin;
    }

    public ManagerLoader getManagers(){
        return managerLoader;
    }

    public KitsGUI getKitsGUI() {
        return kitsGUI;
    }
}
