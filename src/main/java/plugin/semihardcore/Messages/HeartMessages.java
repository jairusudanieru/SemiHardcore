package plugin.semihardcore.Messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class HeartMessages {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    public HeartMessages(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "message.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        this.plugin = plugin;
    }

    public String heartRemoved() {
        String message = config.getString("heartRemoved");
        return (message != null && !message.isEmpty()) ? message : null;
    }

    public String heartAdded() {
        String message = config.getString("heartAdded");
        return (message != null && !message.isEmpty()) ? message : null;
    }

    public String heartWithdraw() {
        String message = config.getString("heartWithdraw");
        return (message != null && !message.isEmpty()) ? message : null;
    }

    public String maxHeartAlready() {
        String message = config.getString("maxHeartAlready");
        return (message != null && !message.isEmpty()) ? message : null;
    }

}
