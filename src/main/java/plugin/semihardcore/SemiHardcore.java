package plugin.semihardcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.semihardcore.Commands.SetPrison;
import plugin.semihardcore.Commands.Withdraw;
import plugin.semihardcore.Events.PlayerRespawn;
import plugin.semihardcore.Events.PlayerUseHeart;
import plugin.semihardcore.Recipes.PlayerHeart;

public final class SemiHardcore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        saveResource("message.yml", false);
        PlayerHeart.registerRecipe(this);
        Bukkit.getPluginCommand("setprison").setExecutor(new SetPrison(this));
        Bukkit.getPluginCommand("withdraw").setExecutor(new Withdraw(this));
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerRespawn(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerUseHeart(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
