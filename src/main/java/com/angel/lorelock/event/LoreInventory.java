package com.angel.lorelock.event;

import com.angel.lorelock.libs.Config;
import com.angel.lorelock.method.HasLore;
import com.angel.lorelock.method.Replace;
import com.angel.lorelock.method.RollString;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LoreInventory implements Listener {
    RollString rollString = new RollString();
    Replace replace = new Replace();
    Config config = new Config();
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        int hotBarButton = event.getHotbarButton();
        Inventory clickedInventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if (player.hasPermission("lorelock.bypass")) return;
        if (event.getInventory().getType() != InventoryType.CRAFTING && event.getInventory().getType() != InventoryType.MERCHANT) {
            if (hotBarButton >= 0 && clickedInventory != null) {
                ItemStack itemTemp = player.getInventory().getItem(event.getHotbarButton());
                if (HasLore.hasLore(itemTemp)) {
                    event.setCancelled(true);
                }
            }
            if (event.getClickedInventory() == null) return;
            if (event.getClickedInventory().getType() == InventoryType.PLAYER || event.getClick() == ClickType.NUMBER_KEY || event.getClick() == ClickType.CONTROL_DROP) {
                if (HasLore.hasLore(event.getCurrentItem())) {
                    event.setCancelled(true);
                    player.getPlayer().sendMessage(replace.replace(rollString.rollString(config.getAntiLoreMessages())));
                }
            }
        }
    }
}
