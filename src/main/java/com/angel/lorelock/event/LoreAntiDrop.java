package com.angel.lorelock.event;

import com.angel.lorelock.utils.Commons;
import com.angel.lorelock.utils.HasLore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.Objects;


public class LoreAntiDrop extends Commons implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("lorelock.bypass.Drop")) return;

        ItemStack itemToDrop = event.getItemDrop().getItemStack();
        if (!HasLore.hasLore(itemToDrop)) return;

        PlayerInventory inventory = player.getInventory();
        if (isInventoryFull(inventory)) {
            dropNonLoreItem(event, inventory);
        } else {
            cancelDropAndSendMsg(event, player);
        }
    }
    private void dropNonLoreItem(PlayerDropItemEvent event, PlayerInventory inventory) {
        ItemStack[] contents = inventory.getContents();
        for (int i = 0; i < contents.length; i++) {
            if (!HasLore.hasLore(contents[i])) {
                event.setCancelled(true);
                inventory.setItem(i, null);
                break;
            }
        }
    }
    private void cancelDropAndSendMsg(PlayerDropItemEvent event, Player player) {
        event.setCancelled(true);
        String message = replace.replace(rollString.rollString(config.getAntiLoreMessages()));
        player.sendMessage(message);
    }
    public boolean isInventoryFull(PlayerInventory inventory) {
        return Arrays.stream(inventory.getStorageContents()).allMatch(Objects::nonNull);
    }
}
