package plugin.semihardcore.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugin.semihardcore.Configuration.MaxHearts;
import plugin.semihardcore.Messages.WithdrawMessages;
import plugin.semihardcore.Recipes.HeartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Withdraw implements TabCompleter, CommandExecutor {

    private final JavaPlugin plugin;
    public Withdraw(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        //Checking if the args is 1, then show the array for tab completion
        MaxHearts maxHearts = new MaxHearts(plugin);
        int hearts = maxHearts.maxHeart();
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (int i = 1; i <= (hearts-1); i++) list.add(String.valueOf(i));
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("withdraw")) return false;
        if (!(sender instanceof Player) || args.length != 1) return false;
        if (!args[0].matches("\\d+")) return false;
        Player player = (Player) sender;
        int numHearts = Integer.parseInt(args[0]);
        HeartItem heartItem = new HeartItem();
        ItemStack item = heartItem.giveHeartItem(plugin, numHearts);
        WithdrawMessages messages = new WithdrawMessages(plugin);

        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        double maxHealthValue = maxHealth != null ? maxHealth.getBaseValue() : 0;
        double health = player.getHealth();

        //Checking if health is not full
        if (health != maxHealthValue) {
            player.sendMessage(Component.text(messages.mustHaveFullHearts()));
            return true;
        }

        int maxHealthIntValue = (int) maxHealthValue;
        if (maxHealthIntValue > 2 && (numHearts * 2) < maxHealthIntValue) {
            //Checking if the inventory of the player is full
            if (player.getInventory().firstEmpty() == -1) {
                world.dropItemNaturally(playerLocation, item);
            } else {
                player.getInventory().addItem(item);
            }

            //Setting the player new heart count
            double newValue = maxHealth.getBaseValue() - (numHearts * 2);
            maxHealth.setBaseValue(newValue);
            player.setHealth(newValue);
            player.spawnParticle(Particle.HEART, playerLocation, 30);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 1f);
            return true;
        }
        player.sendMessage(Component.text(messages.noEnoughHearts()));
        return true;
    }

}