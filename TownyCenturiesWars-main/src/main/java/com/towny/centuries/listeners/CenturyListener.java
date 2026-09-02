package com.towny.centuries.listeners;

import com.towny.centuries.managers.CenturyManager;
import com.towny.centuries.models.Century;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CenturyListener implements Listener {

    private CenturyManager centuryManager;

    public CenturyListener(CenturyManager centuryManager) {
        this.centuryManager = centuryManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Эпохи развития нации")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType().isAir()) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        String centuryName = event.getCurrentItem().getItemMeta().getDisplayName();

        player.sendMessage("§eВы нажали на: " + centuryName);
        player.sendMessage("§7Чтобы перейти на эту эпоху, требуются ресурсы...");

        // TODO: Реализовать логику перехода на эпоху
    }
}