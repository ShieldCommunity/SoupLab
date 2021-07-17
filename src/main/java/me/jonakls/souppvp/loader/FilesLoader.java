package me.jonakls.souppvp.loader;

import me.jonakls.souppvp.SoupPvP;
import me.jonakls.souppvp.manager.FileManager;
import me.jonakls.souppvp.api.Loader;

public class FilesLoader implements Loader {

    private final SoupPvP plugin;
    private FileManager config;
    private FileManager gui;
    private FileManager lang;
    private FileManager kits;

    public FilesLoader(SoupPvP plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        config = new FileManager(plugin, "config.yml");
        gui = new FileManager(plugin, "gui.yml");
        lang = new FileManager(plugin , "lang.yml");
        kits = new FileManager(plugin, "kits.yml");
    }


    public FileManager getConfig() {
        return config;
    }

    public FileManager getGui() {
        return gui;
    }

    public FileManager getLang() {
        return lang;
    }

    public FileManager getKits() {
        return kits;
    }
}
