package com.angel.lorelock;

import com.angel.lorelock.event.LoreAntiDrop;
import com.angel.lorelock.event.LoreInventory;
import com.angel.lorelock.libs.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends JavaPlugin {
//    public static Config config;
    public static FileConfiguration config;

    public void Enable() {
        config = getConfig();
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
