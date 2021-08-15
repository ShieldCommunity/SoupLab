package me.jonakls.souplab.loader;

import me.jonakls.souplab.PluginCore;
import me.jonakls.souplab.command.MainCommand;
import me.jonakls.souplab.command.MainCompleter;
import me.jonakls.souplab.command.builder.ExecutorBuilder;
import me.jonakls.souplab.api.Loader;
import me.jonakls.souplab.command.builder.TabCompleteBuilder;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private final PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new ExecutorBuilder("soup", new MainCommand(pluginCore))
        );

        registerTabCompleter(
                new TabCompleteBuilder("soup", new MainCompleter())
        );
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }

    public void registerTabCompleter(TabCompleteBuilder...tabCompleteBuilders) {

        for (TabCompleteBuilder tabCompleteBuilder : tabCompleteBuilders) {
            Bukkit.getPluginCommand(tabCompleteBuilder.getCommandName()).setTabCompleter(tabCompleteBuilder.getTabCompleter());
        }
    }
}
