package com.angel.lorelock.event;

import com.angel.lorelock.utils.Config;
import com.angel.lorelock.utils.Commons;
import com.angel.lorelock.utils.HasLore;
import com.angel.lorelock.utils.Replace;
import com.angel.lorelock.utils.RollString;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class LoreInventory extends Commons implements Listener {
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
                    Objects.requireNonNull(player.getPlayer()).sendMessage(replace.replace(rollString.rollString(config.getAntiLoreMessages())));
                }
            }
            if (event.getClickedInventory() == null) return;
            if (event.getClickedInventory().getType() == InventoryType.PLAYER || event.getClick() == ClickType.NUMBER_KEY || event.getClick() == ClickType.CONTROL_DROP) {
                if (HasLore.hasLore(event.getCurrentItem())) {
                    event.setCancelled(true);

                }
            }
        }
    }
}
