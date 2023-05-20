package plugin.semihardcore.Configuration;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PrisonLocation {

    private final FileConfiguration config;
    public PrisonLocation(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public Location prison(Player player) {
        Location location = config.getLocation("prison");
        if (location == null) return player.getWorld().getSpawnLocation();
        return location;
    }

}
