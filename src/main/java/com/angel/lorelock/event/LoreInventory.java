package com.angel.lorelock.event;

import com.angel.lorelock.utils.Commons;
import com.angel.lorelock.utils.HasLore;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LoreInventory extends Commons implements Listener {
    private void send(Player player) {
        Objects.requireNonNull(player.getPlayer()).sendMessage(replace.replace(rollString.rollString(config.getAntiLoreMessages())));
    }


    //禁止合成
    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory craftingInventory = event.getInventory();
        ItemStack[] ingredients = craftingInventory.getMatrix();
        for (ItemStack ingredient : ingredients) {
            if (ingredient != null && (HasLore.hasRPGItem(ingredient) || HasLore.hasLore(ingredient))) {
                event.getInventory().setResult(null);
                break;
            }
        }
    }


    //AI磁石炒饭
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (item.getType() != Material.COMPASS) return;
        if (!HasLore.hasLore(item)) return;
        if (player.hasPermission("lorelock.bypass.Compass")) return;
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() != Material.LODESTONE) return;
        event.getPlayer().sendMessage("你不能磁化她");
        event.setCancelled(true);
    }


    //禁止放在展示框里
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (entity.getType() == EntityType.ITEM_FRAME) {
            if (HasLore.hasLore(item)) {
                event.setCancelled(true);
                send(event.getPlayer());
            }
        }
    }

    //禁止放盔甲架上
    @EventHandler
    public void PlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (HasLore.hasLore(item)) {
            event.setCancelled(true);
            send(event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // 去你妈的NULL
        Optional<Inventory> clickedInventoryOpt = Optional.ofNullable(event.getClickedInventory());
        if (player.hasPermission("lorelock.bypass.Inventory")) return;
        // 来点花活
        List<InventoryType> validInventoryTypes = Arrays.asList(InventoryType.CRAFTING, InventoryType.MERCHANT);
        if (validInventoryTypes.stream().noneMatch(type -> type == event.getInventory().getType())) {
            handleHotBarInteraction(event, player, clickedInventoryOpt);
            handlePlayerInventoryInteraction(event, clickedInventoryOpt);
        }
    }

    private void handleHotBarInteraction(InventoryClickEvent event, Player player, Optional<Inventory> clickedInventoryOpt) {
        int hotBarButton = event.getHotbarButton();
        // 不要在点了
        if (hotBarButton >= 0 && clickedInventoryOpt.isPresent()) {
            ItemStack itemTemp = player.getInventory().getItem(hotBarButton);
            if (HasLore.hasLore(itemTemp) || HasLore.hasRPGItem(itemTemp)) {
                event.setCancelled(true);
                send(player);
            }
        }
    }

    private void handlePlayerInventoryInteraction(InventoryClickEvent event, Optional<Inventory> clickedInventoryOpt) {
        ClickType clickType = event.getClick();
        // 数字键监测
        if (clickedInventoryOpt.filter(inv -> inv.getType() == InventoryType.PLAYER).isPresent()
                || clickType == ClickType.NUMBER_KEY || clickType == ClickType.CONTROL_DROP) {
            if (HasLore.hasLore(event.getCurrentItem()) || HasLore.hasRPGItem(event.getCurrentItem())) {
                event.setCancelled(true);
            }
        }
    }
}
