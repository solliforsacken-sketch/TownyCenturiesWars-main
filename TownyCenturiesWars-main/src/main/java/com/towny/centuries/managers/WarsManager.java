package com.towny.centuries.managers;

import com.towny.centuries.TownyCenturiesWarsPlugin;
import com.towny.object.Nation;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class WarsManager {

    public static class War {
        public String attacker;
        public String defender;
        public long startTime;
        public int duration; // в минутах
        public int attackerKills;
        public int defenderKills;
        public boolean active;

        public War(String attacker, String defender, int duration) {
            this.attacker = attacker;
            this.defender = defender;
            this.startTime = System.currentTimeMillis();
            this.duration = duration;
            this.attackerKills = 0;
            this.defenderKills = 0;
            this.active = true;
        }
    }

    private TownyCenturiesWarsPlugin plugin;
    private Map<String, War> activeWars;
    private File warsFile;
    private FileConfiguration warsConfig;

    public WarsManager(TownyCenturiesWarsPlugin plugin) {
        this.plugin = plugin;
        this.activeWars = new HashMap<>();
        loadWars();
    }

    private void loadWars() {
        warsFile = new File(plugin.getDataFolder(), "wars.yml");
        if (!warsFile.exists()) {
            try {
                warsFile.createNewFile();
            } catch (Exception e) {
                plugin.getLogger().severe("Ошибка создания wars.yml: " + e.getMessage());
            }
        }
        warsConfig = YamlConfiguration.loadConfiguration(warsFile);
    }

    public boolean declareWar(String attacker, String defender, int duration) {
        String warKey = attacker + "_vs_" + defender;
        
        if (activeWars.containsKey(warKey)) {
            return false; // Война уже идёт
        }

        War war = new War(attacker, defender, duration);
        activeWars.put(warKey, war);

        // Сохраняем в файл
        saveWars();

        return true;
    }

    public War getWar(String attacker, String defender) {
        return activeWars.get(attacker + "_vs_" + defender);
    }

    public List<War> getNationWars(String nationName) {
        List<War> wars = new ArrayList<>();
        for (War war : activeWars.values()) {
            if (war.attacker.equals(nationName) || war.defender.equals(nationName)) {
                wars.add(war);
            }
        }
        return wars;
    }

    public void endWar(String attacker, String defender) {
        String warKey = attacker + "_vs_" + defender;
        activeWars.remove(warKey);
        saveWars();
    }

    public void recordKill(String nationName, String victim) {
        for (War war : activeWars.values()) {
            if (war.attacker.equals(nationName)) {
                war.attackerKills++;
            } else if (war.defender.equals(nationName)) {
                war.defenderKills++;
            }
        }
    }

    public String getWarStatus(War war) {
        long elapsed = (System.currentTimeMillis() - war.startTime) / 60000; // в минутах
        long remaining = war.duration - elapsed;

        if (remaining <= 0) {
            war.active = false;
            return "§cВойна закончена";
        }

        return String.format("§e%d:%02d§r - Атакующих: §c%d§r Защитников: §b%d",
                remaining / 60, remaining % 60, war.attackerKills, war.defenderKills);
    }

    public void saveWars() {
        try {
            warsConfig.save(warsFile);
        } catch (Exception e) {
            plugin.getLogger().severe("Ошибка сохранения wars.yml: " + e.getMessage());
        }
    }
}