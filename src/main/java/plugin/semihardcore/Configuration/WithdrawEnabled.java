package plugin.semihardcore.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.semihardcore.Commands.SetPrison;

import java.io.File;

public class WithdrawEnabled {

    private final FileConfiguration config;
    public WithdrawEnabled(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void withdrawEnabled(JavaPlugin plugin) {
        boolean enabled = config.getBoolean("commands.withdraw");
        if (!enabled) return;
        Bukkit.getPluginCommand("setprison").setExecutor(new SetPrison(plugin));
    }

}
