package com.angel.lorelock.utils;

import com.angel.lorelock.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements TabExecutor {
    private final Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if ("reload".equalsIgnoreCase(args[0])) {
                    if(!player.hasPermission("lorelock.reload")) return false;
                    try {
                        plugin.reloadConfig();
                        player.sendMessage("配置文件已重载。");
                    } catch (Exception e) {
                        player.sendMessage("配置文件重载失败，正在释放新的配置文件。");
                        plugin.saveDefaultConfig();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("reload");
        }
        return completions;
    }
}
