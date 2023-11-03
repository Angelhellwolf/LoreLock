package com.angel.lorelock.event;

import com.angel.lorelock.utils.Commons;
import com.angel.lorelock.utils.HasLore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;



public class LoreAntiDrop extends Commons implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().hasPermission("lorelock.bypass.Drop")) return;
        if (!HasLore.hasLore(event.getItemDrop().getItemStack())) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(replace.replace(rollString.rollString(config.getAntiLoreMessages())));
    }

}
