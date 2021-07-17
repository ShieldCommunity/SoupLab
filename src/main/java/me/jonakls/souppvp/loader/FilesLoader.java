package me.jonakls.souppvp.loader;

import me.jonakls.souppvp.SoupPvP;
import me.jonakls.souppvp.manager.FileManager;
import me.jonakls.souppvp.api.Loader;

public class FilesLoader implements Loader {

    private final SoupPvP plugin;
    private FileManager configFile;
    private FileManager guiFile;
    private FileManager langFile;
    private FileManager kitsFile;

    public FilesLoader(SoupPvP plugin){
        this.plugin = plugin;
    }
    @Override
    public void load() {
        configFile = new FileManager(plugin, "config.yml");
        guiFile = new FileManager(plugin, "gui.yml");
        langFile = new FileManager(plugin , "lang.yml");
        kitsFile = new FileManager(plugin, "kits.yml");
    }


    public FileManager getConfigFile() {
        return configFile;
    }

    public FileManager getGuiFile() {
        return guiFile;
    }

    public FileManager getLangFile() {
        return langFile;
    }

    public FileManager getKitsFile() {
        return kitsFile;
    }
}
