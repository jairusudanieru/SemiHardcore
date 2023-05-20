package plugin.semihardcore.Recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class HeartItem {

    public ItemStack heartItem(JavaPlugin plugin) {
        String heartName = plugin.getConfig().getString("heartName");
        if (heartName == null || heartName.isEmpty()) heartName = "Player Heart";
        ItemStack result = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta meta = result.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(heartName);
        result.setItemMeta(meta);
        return result;
    }

    public ItemStack giveHeartItem(JavaPlugin plugin, int hearts) {
        String heartName = plugin.getConfig().getString("heartName");
        if (heartName == null || heartName.isEmpty()) heartName = "Player Heart";
        ItemStack result = new ItemStack(Material.HEART_OF_THE_SEA, hearts);
        ItemMeta meta = result.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(heartName);
        result.setItemMeta(meta);
        return result;
    }
}
