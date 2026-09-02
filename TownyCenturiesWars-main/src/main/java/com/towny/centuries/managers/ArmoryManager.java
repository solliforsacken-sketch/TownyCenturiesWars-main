package com.towny.centuries.managers;

import com.towny.centuries.TownyCenturiesWarsPlugin;
import com.towny.centuries.models.Century;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArmoryManager {

    private TownyCenturiesWarsPlugin plugin;

    public ArmoryManager(TownyCenturiesWarsPlugin plugin) {
        this.plugin = plugin;
    }

    public void openArmory(Player player) {
        Inventory armory = plugin.getServer().createInventory(null, 54, "§c⚔ Оружейная ⚔");

        // Огнестрельное оружие
        armory.addItem(createWeapon("§cЛуч", Material.CROSSBOW, "Дальнобойное оружие"));
        armory.addItem(createWeapon("§cРевольвер", Material.GOLDEN_CARROT, "Загруженное оружие"));
        armory.addItem(createWeapon("§cДинамит", Material.TNT, "Взрывчатое вещество"));

        // Доспехи
        armory.addItem(createWeapon("§aНезеритовый шлем", Material.NETHERITE_HELMET, "Защита"));
        armory.addItem(createWeapon("§aНезеритовая кираса", Material.NETHERITE_CHESTPLATE, "Защита"));
        armory.addItem(createWeapon("§aНезеритовые штаны", Material.NETHERITE_LEGGINGS, "Защита"));
        armory.addItem(createWeapon("§aНезеритовые сапоги", Material.NETHERITE_BOOTS, "Защита"));

        // Гранаты
        armory.addItem(createWeapon("§eЛимонка", Material.SNOWBALL, "Граната"));
        armory.addItem(createWeapon("§eФугас", Material.SLIME_BALL, "Граната"));

        // Топливо
        armory.addItem(createWeapon("§6Уголь", Material.COAL, "Топливо"));
        armory.addItem(createWeapon("§6Масло", Material.FIRE_CHARGE, "Топливо"));

        player.openInventory(armory);
    }

    private ItemStack createWeapon(String name, Material material, String description) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.add("§7" + description);
            lore.add("§eЛевый клик: информация");
            lore.add("§eПравый клик: купить");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    public boolean canAccessArmory(String nationName) {
        // Проверка эпохи нации (должна быть Возрождение или выше)
        // TODO: Интеграция с CenturyManager
        return true;
    }

    public void buyWeapon(Player player, String weaponName) {
        // TODO: Логика покупки оружия
        // Проверка денег (требует экономики)
        // Выдача оружия в инвентарь
    }
}