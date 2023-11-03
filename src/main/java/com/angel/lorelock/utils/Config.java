package com.angel.lorelock.utils;

import com.angel.lorelock.Main;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private final Main plugin;

    public Config(Main plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
    }

    private String replace(String input) {
        return input.replace("&", "§");
    }

    private String NoNull(String input){
        if(input != null){
            return input;
        }
        return "-1";
    }

    public String getLore() {
        return replace(NoNull(getConfig().getString("Lore")));
    }

    public boolean isDeathDropEnabled() {
        if (getConfig().isBoolean("DeathDrop")) {
            return getConfig().getBoolean("DeathDrop");
        } else {
            return false;
        }
    }

    public List<String> getDeathDropWorlds() {
        return getConfig().getStringList("DeathDropWorld.World");
    }

    public List<String> getAntiLoreMessages() {
        return getConfig().getStringList("AntiLore.message");
    }

    public void reload() {
        plugin.reloadConfig();
    }

    private FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}
