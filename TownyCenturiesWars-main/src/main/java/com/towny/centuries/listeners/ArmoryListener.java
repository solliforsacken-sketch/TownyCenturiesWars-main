package com.towny.centuries.listeners;

import com.towny.centuries.managers.ArmoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArmoryListener implements Listener {

    private ArmoryManager armoryManager;

    public ArmoryListener(ArmoryManager armoryManager) {
        this.armoryManager = armoryManager;
    }

    @EventHandler
    public void onArmoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Оружейная")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType().isAir()) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        String weaponName = event.getCurrentItem().getItemMeta().getDisplayName();

        if (event.isLeftClick()) {
            player.sendMessage("§e" + weaponName + "§7 - информация об оружии");
        } else if (event.isRightClick()) {
            armoryManager.buyWeapon(player, weaponName);
            player.sendMessage("§aПокупка: " + weaponName);
        }
    }
}