package me.jonakls.souppvp.command;

import me.jonakls.souppvp.PluginCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor{

    private PluginCore pluginCore;

    public MainCommand(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
