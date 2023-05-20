package plugin.semihardcore.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerHeart {

    public static void registerRecipe(JavaPlugin plugin) {
        HeartItem heartItem = new HeartItem();
        ItemStack item = heartItem.heartItem(plugin);

        //Creating the custom recipe for the item
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "player_heart"), item);
        recipe.shape("DAD", "ATA", "DAD");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('A', Material.ANCIENT_DEBRIS);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        //Adding the recipe to the server
        Bukkit.getServer().addRecipe(recipe);
    }
}
