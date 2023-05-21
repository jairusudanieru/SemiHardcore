package plugin.semihardcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.semihardcore.Configuration.Commands;
import plugin.semihardcore.Events.PlayerRespawn;
import plugin.semihardcore.Events.PlayerUseHeart;
import plugin.semihardcore.Recipes.PlayerHeart;

public final class SemiHardcore extends JavaPlugin {

    Commands commands = new Commands(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        saveResource("message.yml", false);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerRespawn(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerUseHeart(this),this);
        PlayerHeart.registerRecipe(this);
        commands.withdrawCommand(this);
        commands.setPrisonCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
