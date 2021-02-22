package playertime.playertime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class cmd implements Listener, CommandExecutor {
    File file = new File("plugins/PlayerTime/data.yml");
    FileConfiguration data = YamlConfiguration.loadConfiguration(file);
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player p = (Player) s;
        if (args.length == 0) {
            p.sendMessage("§f/time help");
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            p.sendMessage("§2/time help   §d時間設定のヘルプの確認");
            p.sendMessage("§2/time day   §d時間を朝にする");
            p.sendMessage("§2/time night   §d時間を夜にする");
            p.sendMessage("§2/time reset   §d設定をリセットする");
            return true;
        }
        if (args[0].equalsIgnoreCase("night")) {
            p.sendMessage("§d時間を夜に設定しました！");
            p.setPlayerTime(15000, true);
            new login().check(p, "night");
            return true;
        }
        if (args[0].equalsIgnoreCase("day")) {
            p.sendMessage("§d時間を朝に設定しました！");
            p.setPlayerTime(0, true);
            new login().check(p, "day");
            return true;
        }
        if (args[0].equalsIgnoreCase("reset")) {
            p.sendMessage("§d時間をリセットしました！");
            p.setPlayerTime(15000, false);
            return true;
        }
        return false;
    }
}