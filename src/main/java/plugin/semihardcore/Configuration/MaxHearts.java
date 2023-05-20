package plugin.semihardcore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MaxHearts {

    private final FileConfiguration config;
    public MaxHearts(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public int maxHeart() {
        int hearts = config.getInt("maxHearts");
        if (hearts <= 0) return 10;
        return hearts;
    }

}
