package me.jonakls.souppvp.command;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor{

    private final PluginCore pluginCore;

    public MainCommand(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileManager config = pluginCore.getFilesLoader().getConfig();
        FileManager lang = pluginCore.getFilesLoader().getLang();

        String prefix = lang.getString("prefix");

        Player player = (Player) sender;

        if (!(args.length > 0)) {

            sender.sendMessage(prefix + lang.getString("error.unknown-command"));
            return false;
        }

        switch (args[0].toLowerCase()){
            case "reload":
                sender.sendMessage(prefix + lang.getString("messages.reload"));
                lang.reload();
                config.reload();
                pluginCore.getFilesLoader().getKits().reload();
                pluginCore.getFilesLoader().getGui().reload();
                break;
            case "kits":
                player.openInventory(pluginCore.getKitsGUI().kits());
                break;
        }

        return false;
    }
}
