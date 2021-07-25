package be.shark_zekrom.bench;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ArmorStand) {
            File file = new File(Main.getInstance().getDataFolder(), "bench.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);


            Double locx = event.getRightClicked().getLocation().getX();
            Double locy = event.getRightClicked().getLocation().getY();
            Double locz = event.getRightClicked().getLocation().getZ();
            String locworld = event.getRightClicked().getLocation().getWorld().getName();

            String loc = config.getString("bench." + locworld + "." + locx + "." + locy + "." + locz);
            if (loc != null) {
                event.getRightClicked().addPassenger(player);
            }
        }
    }
}

