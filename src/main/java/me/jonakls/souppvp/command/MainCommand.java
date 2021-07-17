package me.jonakls.souppvp.command;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor{

    private final PluginCore pluginCore;

    public MainCommand(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileManager config = pluginCore.getFilesLoader().getConfig();
        FileManager lang = pluginCore.getFilesLoader().getLang();

        if (args.length > 0) {



        }

        return false;
    }
}
