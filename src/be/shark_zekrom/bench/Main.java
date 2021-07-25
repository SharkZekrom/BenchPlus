package be.shark_zekrom.bench;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    private static Main instance;

    public static Main getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getLogger().info("Bench+ enabled !");
        PluginManager pm = getServer().getPluginManager();

        this.getCommand("bench+").setExecutor(new Commands());
        pm.registerEvents(new Listener(), this);

    }


    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Bench+ disabled !");
    }
}