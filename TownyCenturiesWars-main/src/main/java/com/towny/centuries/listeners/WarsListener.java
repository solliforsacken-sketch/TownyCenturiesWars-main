package com.towny.centuries.listeners;

import com.towny.centuries.managers.WarsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WarsListener implements Listener {

    private WarsManager warsManager;

    public WarsListener(WarsManager warsManager) {
        this.warsManager = warsManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer == null) {
            return;
        }

        // TODO: Получить нацию убийцы и жертвы через Towny
        // TODO: Если они в войне - запишем убийство
        // warsManager.recordKill(killerNation, victimNation);
    }
}