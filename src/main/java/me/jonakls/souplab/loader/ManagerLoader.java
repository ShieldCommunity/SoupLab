package me.jonakls.souplab.loader;

import me.jonakls.souplab.PluginCore;
import me.jonakls.souplab.api.Loader;

public class ManagerLoader implements Loader {

    private PluginCore pluginCore;

    public ManagerLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {

    }
}
