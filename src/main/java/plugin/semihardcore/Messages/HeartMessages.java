package plugin.semihardcore.Messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class HeartMessages {

    //private final JavaPlugin plugin;
    private final FileConfiguration config;
    public HeartMessages(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "message.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        //this.plugin = plugin;
    }

    public String heartRemoved() {
        String message = config.getString("heartRemoved");
        if (message == null) return null;
        message = message.replace("&","§");
        return message;
    }

    public String heartAdded() {
        String message = config.getString("heartAdded");
        if (message == null) return null;
        message = message.replace("&","§");
        return message;
    }

    public String maxHeartAlready() {
        String message = config.getString("maxHeartAlready");
        if (message == null) return null;
        message = message.replace("&","§");
        return message;
    }

}
