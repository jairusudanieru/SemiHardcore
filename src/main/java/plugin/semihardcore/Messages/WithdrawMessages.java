package plugin.semihardcore.Messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WithdrawMessages {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    public WithdrawMessages(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "message.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        this.plugin = plugin;
    }

    public String noEnoughHearts() {
        String message = config.getString("noEnoughHearts");
        return (message != null && !message.isEmpty()) ? message : null;
    }

    public String mustHaveFullHearts() {
        String message = config.getString("mustHaveFullHearts");
        return (message != null && !message.isEmpty()) ? message : null;
    }


}
