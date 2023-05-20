package plugin.semihardcore.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugin.semihardcore.Configuration.MaxHearts;
import plugin.semihardcore.Messages.HeartMessages;

public class PlayerUseHeart implements Listener {

    private final JavaPlugin plugin;
    public PlayerUseHeart(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRegenerate(@NotNull PlayerInteractEvent event) {
        //Event variables
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        MaxHearts maxHearts = new MaxHearts(plugin);
        HeartMessages messages = new HeartMessages(plugin);
        if (item == null || !action.isRightClick() || !item.getType().equals(Material.HEART_OF_THE_SEA)) return;
        ItemMeta meta = item.getItemMeta();
        int maxHeart = maxHearts.maxHeart();

        //Checking if the item is the correct item
        if (!meta.hasEnchant(Enchantment.DURABILITY)) return;
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        //Checking if the player have enough hearts
        if (maxHealth != null && maxHealth.getBaseValue() < (maxHeart*2)) {
            double newValue = maxHealth.getBaseValue() + 2;
            maxHealth.setBaseValue(newValue);
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.spawnParticle(Particle.HEART, player.getLocation(), 30);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage(Component.text(messages.heartAdded()));
        } else {
            player.sendMessage(Component.text(messages.maxHeartAlready()));
        }
    }

}
