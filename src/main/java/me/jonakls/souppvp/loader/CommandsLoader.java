package me.jonakls.souppvp.loader;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.command.MainCommand;
import me.jonakls.souppvp.command.MainCompleter;
import me.jonakls.souppvp.command.builder.ExecutorBuilder;
import me.jonakls.souppvp.api.Loader;
import me.jonakls.souppvp.command.builder.TabCompleteBuilder;
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
