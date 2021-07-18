package me.jonakls.souppvp.command;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.manager.FileManager;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

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
                lang.reload();
                config.reload();
                pluginCore.getFilesLoader().getKits().reload();
                pluginCore.getFilesLoader().getGui().reload();
                sender.sendMessage(prefix + lang.getString("messages.reload"));
                break;
            case "kits":
                player.openInventory(pluginCore.getKitsGUI().kits());
                break;
            case "status":

                for (MetadataValue line : player.getMetadata("status")) {

                    player.sendMessage("Status-1: " + line);
                    player.sendMessage("Status-2: " + line.toString());
                    player.sendMessage("Status-3: " + line.value().toString());
                }

                break;
            case "setspawn":
                if (!(config.contains("spawn.world"))) {

                    config.set("spawn.world", player.getWorld().getName());
                    config.set("spawn.x", player.getLocation().getX());
                    config.set("spawn.y", player.getLocation().getY());
                    config.set("spawn.z", player.getLocation().getZ());
                    config.set("spawn.yaw", player.getLocation().getYaw());
                    config.set("spawn.pitch", player.getLocation().getPitch());
                    config.save();

                    player.sendMessage(lang.getString("messages.set-spawn"));

                    return true;
                }

                config.set("spawn.world", player.getWorld().getName());
                config.set("spawn.x", player.getLocation().getX());
                config.set("spawn.y", player.getLocation().getY());
                config.set("spawn.z", player.getLocation().getZ());
                config.set("spawn.yaw", player.getLocation().getYaw());
                config.set("spawn.pitch", player.getLocation().getPitch());
                config.save();

                player.sendMessage(lang.getString("messages.change-spawn"));

                break;
            case "spawn":
                if (!(config.contains("spawn.world"))) {
                    player.sendMessage(prefix + lang.getString("error.no-spawn"));
                    return true;
                }

                player.teleport(new Location(
                        Bukkit.getWorld(config.getString("spawn.world")),
                        config.getDouble("spawn.x"),
                        config.getDouble("spawn.y"),
                        config.getDouble("spawn.z"),
                        (float) config.getDouble("spawn.yaw"),
                        (float) config.getDouble("spawn.pirch")
                ));
                break;
            default:
                List<String> help = new ArrayList<>();

                help.add(prefix + "&7help commands");
                help.add("&c- /soup help &8| &7Show help commands");
                help.add("&c- /soup reload &8| &7Reload all files of config");
                help.add("&c- /soup kits &8| &7Open gui of kits");

                for (String line : help) {

                    player.sendMessage(Colorized.apply(line));

                }
                break;
        }

        return false;
    }
}
