package be.shark_zekrom.bench;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (args.length > 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("bench+.admin")) {
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(ChatColor.AQUA + "==========[Bench+]==========");
                        player.sendMessage(ChatColor.AQUA + "");
                        player.sendMessage(ChatColor.AQUA + "Commands:");
                        player.sendMessage(ChatColor.AQUA + "");
                        player.sendMessage(ChatColor.AQUA + "/bench+ help §8» §eSee all commands");
                        player.sendMessage(ChatColor.AQUA + "/bench+ create §8» §eCreate a bench");
                        player.sendMessage(ChatColor.AQUA + "/bench+ delete §8» §eDelete a bench");
                    }
                    if (args[0].equalsIgnoreCase("create")) {

                        File file = new File(Main.getInstance().getDataFolder(), "bench.yml");
                        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                        for (Entity entity : player.getNearbyEntities(0.5D, 0.5D, 0.5D)) {
                            if (entity instanceof ArmorStand) {
                                Double locx = entity.getLocation().getX();
                                Double locy = entity.getLocation().getY();
                                Double locz = entity.getLocation().getZ();
                                String locworld = entity.getLocation().getWorld().getName();

                                String loc = config.getString("bench." + locworld + "." + locx + "." + locy + "." + locz);

                                if (loc != null) {
                                    player.sendMessage(ChatColor.AQUA + "[Bench+]" + ChatColor.YELLOW + " There is already a bench at this coordinate.");

                                } else {

                                    config.set("bench." + locworld + "." + locx + "." + locy + "." + locz, true);
                                    player.sendMessage(ChatColor.AQUA + "[Bench+]" + ChatColor.YELLOW + " bench with coordinate " + locx + ", " + locy + ", " + locz + " set up.");

                                    try {
                                        config.save(file);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }
                    if (args[0].equalsIgnoreCase("delete")) {
                        File file = new File(Main.getInstance().getDataFolder(), "bench.yml");
                        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                        for (Entity entity : player.getNearbyEntities(0.5D, 0.5D, 0.5D)) {
                            if (entity instanceof ArmorStand) {
                                Double locx = entity.getLocation().getX();
                                Double locy = entity.getLocation().getY();
                                Double locz = entity.getLocation().getZ();
                                String locworld = entity.getLocation().getWorld().getName();

                                String loc = config.getString("bench." + locworld + "." + locx + "." + locy + "." + locz);

                                if (loc != null) {
                                    config.set("bench." + locworld + "." + locx + "." + locy + "." + locz, null);
                                    player.sendMessage(ChatColor.AQUA + "[Bench+]" + ChatColor.YELLOW + " Bench with coordinate " + locx + ", " + locy + ", " + locz + " deleted.");

                                    try {
                                        config.save(file);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    player.sendMessage(ChatColor.AQUA + "[Bench+]" + ChatColor.YELLOW + " No bench at this coordinate.");
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("create");
            arguments.add("delete");

            return arguments;
        }
        return null;
    }
}