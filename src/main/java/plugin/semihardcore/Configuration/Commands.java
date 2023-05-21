package plugin.semihardcore.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.semihardcore.Commands.SetPrison;
import plugin.semihardcore.Commands.Withdraw;

import java.io.File;

public class Commands {

    private final FileConfiguration config;
    public Commands(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    //Register the "withdraw" command
    public void withdrawCommand(JavaPlugin plugin) {
        boolean enabled = config.getBoolean("commands.withdraw");
        if (!enabled) return;
        PluginCommand command = Bukkit.getPluginCommand("withdraw");
        if (command != null) command.setExecutor(new Withdraw(plugin));
    }

    //Register the "setprison" command
    public void setPrisonCommand(JavaPlugin plugin) {
        boolean enabled = config.getBoolean("prison.enabled");
        if (!enabled) return;
        PluginCommand command = Bukkit.getPluginCommand("setprison");
        if (command != null) command.setExecutor(new SetPrison(plugin));
    }

}
