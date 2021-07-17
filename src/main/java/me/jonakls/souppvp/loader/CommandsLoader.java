package me.jonakls.souppvp.loader;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.command.KitsCommand;
import me.jonakls.souppvp.command.MainCommand;
import me.jonakls.souppvp.command.builder.ExecutorBuilder;
import me.jonakls.souppvp.api.Loader;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private final PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new ExecutorBuilder("soup", new MainCommand(pluginCore)),
                new ExecutorBuilder("kits", new KitsCommand())
        );
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
