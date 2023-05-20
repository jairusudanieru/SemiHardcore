package plugin.semihardcore.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SetPrison implements CommandExecutor {

    private final JavaPlugin plugin;
    public SetPrison(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("setprison")) return false;
        if (!(sender instanceof Player) || args.length != 0) return false;
        Player player = (Player) sender;
        Location prison = player.getLocation();
        plugin.getConfig().set("prison",prison);
        plugin.saveConfig();
        player.sendMessage(Component.text("Prison Location set successfully!"));
        return true;
    }

}
