package com.angel.lorelock.utils;

import com.angel.lorelock.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class HasLore extends Config{
    Commons commons = new Commons();

    public HasLore(Main plugin) {
        super(plugin);
    }

    public static boolean hasLore(ItemStack item) {
        return ItemLoreCheck(item);
    }
    public static boolean hasRPGItem(ItemStack RPGitem){
        return ItemRPGCheck(RPGitem);
    }

    private static boolean ItemRPGCheck(ItemStack RPGitem) {
        String search = Commons.config.getRPItem();
        if (RPGitem != null && RPGitem.hasItemMeta()) {
            ItemMeta meta = RPGitem.getItemMeta();
            if (meta.hasLore()) {
                for (String lore : meta.getLore()) {
                    if (lore.contains(search)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean ItemLoreCheck(ItemStack item) {
        String search = Commons.config.getLore();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasLore()) {
                for (String lore : meta.getLore()) {
                    if (lore.contains(search)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
