package com.angel.lorelock.event;

import com.angel.lorelock.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeadDrop implements Listener {
    RollString rollString = Commons.rollString;
    Replace replace = Commons.replace;
    Config config = Commons.config;

    //初始化，将所有世界设为死亡不掉落,由本插件接管掉落系统
    public void KEEP_INVENTORY() {
        for (World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
        }
    }

    @EventHandler
    public void getDeathDropWorlds(PlayerDeathEvent event) {
        if(config.isDeathDropEnabled()) return;
        World world = event.getEntity().getWorld();
        List<String> deadDropWorlds = config.getDeathDropWorlds();
        if (deadDropWorlds.contains(world.getName())) {
            dropItem(event.getPlayer());
        }
    }

    public void dropItem(Player player) {
        if (player.hasPermission("lorelock.bypass.Dead")) return;
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> itemList = new ArrayList<>(Arrays.asList(inventory.getContents()));
        ItemStack[] itemStack =  itemList.toArray(new ItemStack[0]);
        for (ItemStack item : itemStack) {
            if (item != null) {
                if (!HasLore.hasLore(item)) {
                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                    inventory.remove(item);
                }
            }
        }
        ItemStack[] armorContents = inventory.getArmorContents();
        for (ItemStack armorItem : armorContents) {
            if (armorItem != null) {
                if (!HasLore.hasLore(armorItem)) {
//                    player.getWorld().dropItemNaturally(player.getLocation(), armorItem);
                    armorItem.setType(Material.AIR);
                }
            }
        }
        inventory.setArmorContents(armorContents);
        player.sendMessage(replace.replace(rollString.rollString(config.getDeadMessages())));
    }

}
