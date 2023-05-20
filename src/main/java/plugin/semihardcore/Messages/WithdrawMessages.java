package plugin.semihardcore.Messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WithdrawMessages {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    public WithdrawMessages(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "message.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        this.plugin = plugin;
    }

    public String noEnoughHearts() {
        String message = config.getString("noEnoughHearts");
        if (message == null) return null;
        message = message.replace("&","§");
        return message;
    }

    public String mustHaveFullHearts() {
        String message = config.getString("mustHaveFullHearts");
        if (message == null) return null;
        message = message.replace("&","§");
        return message;
    }

    public String heartWithdraw(int numHearts) {
        String message = config.getString("heartWithdraw");
        String amount = String.valueOf(numHearts);
        message = message.replace("&","§").replace("%amount%",amount);
        return message;
    }


}
