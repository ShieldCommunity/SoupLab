package me.jonakls.souppvp.listener;

import me.jonakls.souppvp.PluginCore;
import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.manager.FileManager;
import me.jonakls.souppvp.utils.Colorized;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private final PluginCore pluginCore;

    public PlayerInteractListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getItem() == null) return;
        if (!event.getItem().getType().equals(Material.MUSHROOM_SOUP)) return;

        event.setCancelled(true);
        event.setUseItemInHand(Event.Result.DENY);

        Player player = event.getPlayer();
        if (player.getHealth() == player.getMaxHealth()) return;

        double health = player.getHealth() + 5;

        if (health > player.getMaxHealth()){

            double difference = player.getMaxHealth() - player.getHealth();
            player.setHealth(difference + player.getHealth());
            event.getItem().setType(Material.BOWL);
            return;
        }

        player.setHealth(health);
        event.getItem().setType(Material.BOWL);

    }

    @EventHandler
    public void onInteractSign(PlayerInteractEvent event) {
        FilesLoader files = pluginCore.getFilesLoader();
        if (event.getClickedBlock() == null) return;
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (!event.getClickedBlock().getType().equals(Material.WALL_SIGN)) return;

        Sign sign = (Sign) event.getClickedBlock().getState();

        String[] lines = files.getConfig().getString("signs.soups").split(";");

        if (Colorized.apply(sign.getLine(1)).equals(Colorized.apply(lines[1]))) {

            event.getPlayer().openInventory(pluginCore.getKitsGUI().refill());
        }
    }

    @EventHandler
    public void onInteractHotbar(PlayerInteractEvent event) {
        FileManager config = pluginCore.getFilesLoader().getConfig();

        if (event.getItem() == null) return;
        if (!event.getItem().hasItemMeta()) return;
        if (!event.getItem().getItemMeta().getDisplayName().equals(config.getString("items-join.kits.display"))) return;

        event.getPlayer().closeInventory();
        event.getPlayer().openInventory(pluginCore.getKitsGUI().kits());

    }

}
