package com.towny.centuries.managers;

import com.towny.centuries.TownyCenturiesWarsPlugin;
import com.towny.centuries.models.Century;
import com.towny.object.Nation;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class CenturyManager {

    private TownyCenturiesWarsPlugin plugin;
    private Map<String, Century.CenturyType> nationCenturies;
    private File centuriesFile;
    private FileConfiguration centuriesConfig;

    public CenturyManager(TownyCenturiesWarsPlugin plugin) {
        this.plugin = plugin;
        this.nationCenturies = new HashMap<>();
        loadCenturies();
    }

    private void loadCenturies() {
        centuriesFile = new File(plugin.getDataFolder(), "centuries.yml");
        if (!centuriesFile.exists()) {
            plugin.saveResource("centuries.yml", false);
        }
        centuriesConfig = YamlConfiguration.loadConfiguration(centuriesFile);
    }

    public void openCenturyMenu(Player player) {
        Inventory menu = plugin.getServer().createInventory(null, 45, "§6Эпохи развития нации");

        for (Century.CenturyType century : Century.CenturyType.values()) {
            ItemStack item = createCenturyItem(century);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }

    private ItemStack createCenturyItem(Century.CenturyType century) {
        ItemStack item = new ItemStack(getMaterialForCentury(century));
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6" + century.getDisplayName());
            List<String> lore = new ArrayList<>();
            
            if (century == Century.CenturyType.PRIMITIVE) {
                lore.add("§7Начальная эпоха");
                lore.add("§7Уже получено");
            } else {
                lore.add("§7Уровень: §c" + century.getLevel());
                lore.add("§7");
                lore.add("§eТребуемые ресурсы:");
                
                Century c = new Century(century);
                int count = 0;
                for (Map.Entry<Material, Integer> entry : c.getRequiredItems().entrySet()) {
                    if (count >= 5) {
                        lore.add("§7и ещё " + (c.getRequiredItems().size() - 5) + " предметов");
                        break;
                    }
                    lore.add("§7- " + entry.getKey().toString() + " x" + entry.getValue());
                    count++;
                }
            }
            
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    private Material getMaterialForCentury(Century.CenturyType century) {
        switch (century) {
            case PRIMITIVE:
                return Material.WOODEN_AXE;
            case STONE:
                return Material.STONE_AXE;
            case BRONZE:
                return Material.GOLD_AXE;
            case IRON:
                return Material.IRON_AXE;
            case FLOURISH:
                return Material.DIAMOND_AXE;
            case EARLY_MEDIEVAL:
                return Material.ENCHANTING_TABLE;
            case MEDIEVAL:
                return Material.END_ROD;
            case LATE_MEDIEVAL:
                return Material.NETHERITE_AXE;
            case RENAISSANCE:
                return Material.CROSSBOW;
            case ATOMIC:
                return Material.TNT;
            default:
                return Material.STICK;
        }
    }

    public boolean unlockCentury(String nationName, Century.CenturyType century) {
        if (century == Century.CenturyType.PRIMITIVE) {
            nationCenturies.put(nationName, century);
            return true;
        }

        // Проверка требуемых ресурсов (интеграция с Towny нужна)
        Century c = new Century(century);
        // TODO: Проверить наличие ресурсов в сундуках нации
        
        nationCenturies.put(nationName, century);
        return true;
    }

    public Century.CenturyType getNationCentury(String nationName) {
        return nationCenturies.getOrDefault(nationName, Century.CenturyType.PRIMITIVE);
    }

    public void saveCenturies() {
        try {
            centuriesConfig.save(centuriesFile);
        } catch (Exception e) {
            plugin.getLogger().severe("Ошибка сохранения centuries.yml: " + e.getMessage());
        }
    }
}