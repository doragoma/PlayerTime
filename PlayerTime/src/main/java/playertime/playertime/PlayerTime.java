package playertime.playertime;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerTime extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new login(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new cmd(),this);
        getCommand("time").setExecutor(new cmd());
    }
}
