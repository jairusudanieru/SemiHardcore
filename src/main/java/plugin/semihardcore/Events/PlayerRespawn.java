package plugin.semihardcore.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugin.semihardcore.Configuration.PrisonLocation;
import plugin.semihardcore.Configuration.SafeRespawn;
import plugin.semihardcore.Messages.HeartMessages;

public class PlayerRespawn implements Listener {

    private final JavaPlugin plugin;
    public PlayerRespawn(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(@NotNull PlayerRespawnEvent event) {
        //Event variables
        Player player = event.getPlayer();
        SafeRespawn safeRespawn = new SafeRespawn(plugin);
        HeartMessages messages = new HeartMessages(plugin);
        PrisonLocation location = new PrisonLocation(plugin);
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        //Checking if the player have more than 1 heart, if not the player gets eliminated
        if (maxHealth == null) return;
        if (maxHealth.getBaseValue() > 2) {
            double newValue = maxHealth.getBaseValue() - 2;
            maxHealth.setBaseValue(newValue);
            player.setHealth(newValue);
            player.sendMessage(Component.text(messages.heartRemoved()));
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.spawnParticle(Particle.TOTEM, player.getLocation(), 30);
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1.5f);
                //Checking the config if the safeRespawn is enabled
                safeRespawn.onSafeRespawn(player);
            }, 1L);
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setBedSpawnLocation(location.prison(player));
                player.teleport(location.prison(player));
                player.setGameMode(GameMode.ADVENTURE);
                player.spawnParticle(Particle.TOTEM, player.getLocation(), 30);
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1.5f);
            }, 1L);
        }
    }

}
