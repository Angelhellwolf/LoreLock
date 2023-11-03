package com.angel.lorelock;

import com.angel.lorelock.event.DeadDrop;
import com.angel.lorelock.event.LoreAntiDrop;
import com.angel.lorelock.event.LoreInventory;
import com.angel.lorelock.utils.Commands;
import com.angel.lorelock.utils.Commons;
import com.angel.lorelock.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Config config;
    public static Config getPluginConfig() {
        return config;
    }
    DeadDrop deadDrop = new DeadDrop();


    public void Enable() {
        this.getCommand("lorelock").setExecutor(new Commands(this));
        this.getCommand("lorelock").setTabCompleter(new Commands(this));
        deadDrop.KEEP_INVENTORY();
        config = new Config(this);
        Commons.config = config;
        Bukkit.getPluginManager().registerEvents(new DeadDrop(), this);
        Bukkit.getPluginManager().registerEvents(new LoreAntiDrop(), this);
        Bukkit.getPluginManager().registerEvents(new LoreInventory(), this);
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(new String[]{
                "§a============================",
                "§aLoreLock插件已启动",
                "§a作者：Angel",
                "§a本插件将使用物品的Lore来锁定物品，防止玩家丢弃、合成、交易、放入箱子等",
                "§a============================"
        });
    }

    public void Disable() {
        saveConfig();
        Bukkit.getConsoleSender().sendMessage("§cLoreLock插件已卸载");
    }

}
