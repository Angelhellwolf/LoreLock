package com.angel.lorelock.utils;

import com.angel.lorelock.Main;
import java.util.List;

import org.bukkit.Bukkit;
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
            Bukkit.getConsoleSender().sendMessage("请检查Config文件中的 DeathDrop，你的DeathDrop似乎存在问题");
            return false;
        }
    }

    public List<String> getDeathDropWorlds() {
        return getConfig().getStringList("DeathDropWorld.World");
    }

    public List<String> getAntiLoreMessages() {
        return getConfig().getStringList("AntiLore.message");
    }
    public List<String> getDeadMessages() {
        return getConfig().getStringList("Dead.message");
    }

    public void reload() {
        plugin.reloadConfig();
    }

    private FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}
