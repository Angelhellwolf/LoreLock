package com.angel.lorelock.event;

import com.angel.lorelock.libs.Config;
import com.angel.lorelock.method.Replace;
import com.angel.lorelock.method.HasLore;
import com.angel.lorelock.method.RollString;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.List;

public class LoreAntiDrop implements Listener {
    RollString rollString = new RollString();
    Replace replace = new Replace();
    Config config = new Config();
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().hasPermission("lorelock.bypass")) return;
        if (!HasLore.hasLore(event.getItemDrop().getItemStack())) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(replace.replace(rollString.rollString(config.getAntiLoreMessages())));
    }

}
