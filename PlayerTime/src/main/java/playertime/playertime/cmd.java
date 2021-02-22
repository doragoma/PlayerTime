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
import java.util.Arrays;
import java.util.List;

public class cmd implements Listener, CommandExecutor {
    File file = new File("plugins/PlayerTime/data.yml");
    FileConfiguration data = YamlConfiguration.loadConfiguration(file);
    File msfile = new File("plugins/PlayerTime/message.yml");
    FileConfiguration msg = YamlConfiguration.loadConfiguration(msfile);

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player p = (Player) s;
        if (args.length == 0) {
            p.sendMessage(msg.getString("command.usage").replace("&", "§"));
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            List<String> list = msg.getStringList("command.help");
            for (String msg : list) {
                p.sendMessage(msg.replace("[", "").replace("]", "").replace("&", "§").replace(",", ""));
            }
        }
        if (args[0].equalsIgnoreCase("night")) {
            if (p.hasPermission("ptime.night") || p.hasPermission("ptime.set")) {
                p.sendMessage(msg.getString("command.night").replace("&", "§"));
                p.setPlayerTime(0, true);
                new login().check(p, "night");
            } else {
                p.sendMessage(msg.getString("command.perm").replace("&", "§"));
            }
        }
        if (args[0].equalsIgnoreCase("day")) {
            if (p.hasPermission("ptime.day") || p.hasPermission("ptime.set")) {
                p.sendMessage(msg.getString("command.day").replace("&", "§"));
                p.setPlayerTime(15000, true);
                new login().check(p, "day");
            } else {
                p.sendMessage(msg.getString("command.perm").replace("&", "§"));
            }
        }
        if (args[0].equalsIgnoreCase("reset")) {
            if (p.hasPermission("ptime.reset") || p.hasPermission("ptime.set")) {
                p.sendMessage(msg.getString("command.reset").replace("&", "§"));
                new login().check(p, "none");
                return true;
            } else {
                p.sendMessage(msg.getString("command.perm").replace("&", "§"));
            }
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (p.hasPermission("ptime.reload")) {
                p.sendMessage(msg.getString("command.reload").replace("&", "§"));
                try {
                    data.load(file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                try {
                    msg.load(msfile);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                p.sendMessage(msg.getString("command.perm").replace("&", "§"));
            }
        }
        return false;
    }
}
