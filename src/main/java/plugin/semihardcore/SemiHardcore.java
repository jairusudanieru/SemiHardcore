package plugin.semihardcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.semihardcore.Commands.SetPrison;
import plugin.semihardcore.Configuration.WithdrawEnabled;
import plugin.semihardcore.Events.PlayerRespawn;
import plugin.semihardcore.Events.PlayerUseHeart;
import plugin.semihardcore.Recipes.PlayerHeart;

public final class SemiHardcore extends JavaPlugin {

    WithdrawEnabled withdrawEnabled = new WithdrawEnabled(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        saveResource("message.yml", false);
        PlayerHeart.registerRecipe(this);
        withdrawEnabled.withdrawEnabled(this);
        Bukkit.getPluginCommand("setprison").setExecutor(new SetPrison(this));
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerRespawn(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerUseHeart(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
