package playertime.playertime;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class login implements Listener {
    public static HashMap<String, String> information = new HashMap<>();
    File file = new File("plugins/PlayerTime/data.yml");
    FileConfiguration data = YamlConfiguration.loadConfiguration(file);

    public void check(Player p, String s) {
        information.put(p.getUniqueId()+"", s);
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e) throws IOException, InvalidConfigurationException {
        data.load(file);
        Player p = e.getPlayer();
        if (data.contains(p.getUniqueId()+"")) {
            information.put(p.getUniqueId()+"", data.getString(p.getUniqueId()+""));
            if (information.get(p.getUniqueId()+"").equalsIgnoreCase("day")) {
                p.setPlayerTime(0, true);
            } else if (information.get(p.getUniqueId()+"").equalsIgnoreCase("night")) {
                p.setPlayerTime(15000, true);
            }
        } else {
        }
    }

    @EventHandler
    public void onquit(PlayerQuitEvent e) throws IOException {
        Player p = e.getPlayer();
        if (information.containsKey(p.getUniqueId()+"")) {
            data.set(p.getUniqueId()+"",information.get(p.getUniqueId()+""));
            data.save(file);
            information.remove(p.getUniqueId()+"");
        } else {
        }
    }
}