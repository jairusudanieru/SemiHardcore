package plugin.semihardcore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class SafeRespawn {

    private final FileConfiguration config;
    public SafeRespawn(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void onSafeRespawn(Player player) {
        boolean safeRespawn = config.getBoolean("safeRespawn");
        if (!safeRespawn) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 0));
    }

}
