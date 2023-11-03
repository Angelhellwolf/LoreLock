package com.angel.lorelock.method;

import com.angel.lorelock.libs.Config;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class HasLore extends Config{
    static Config config = new Config();
    public static boolean hasLore(ItemStack item) {
        return ItemLoreCheck(item);
    }

    private static boolean ItemLoreCheck(ItemStack item) {
        String search = config.getLore();
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
