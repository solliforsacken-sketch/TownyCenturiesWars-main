package com.towny.centuries.managers;

import com.towny.centuries.TownyCenturiesWarsPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {

    private TownyCenturiesWarsPlugin plugin;

    public VehicleManager(TownyCenturiesWarsPlugin plugin) {
        this.plugin = plugin;
    }

    public void openVehicleShop(Player player) {
        Inventory shop = plugin.getServer().createInventory(null, 54, "§b🚜 Магазин транспорта 🚂");

        // Тракторы (доступны с Расцвета)
        shop.addItem(createVehicle("§6Трактор", Material.FURNACE, "Сельскохозяйственный транспорт", 5000));
        shop.addItem(createVehicle("§6Комбайн", Material.HOPPER, "Уборочная машина", 8000));

        // Поезда (доступны с Расцвета)
        shop.addItem(createVehicle("§bПаровоз", Material.DARK_OAK_WOOD, "Железнодорожный транспорт", 10000));
        shop.addItem(createVehicle("§bВагон грузовой", Material.CHEST, "Грузовой вагон", 3000));
        shop.addItem(createVehicle("§bВагон пассажирский", Material.OAK_WOOD, "Пассажирский вагон", 2500));

        // Современный транспорт (Атомный век)
        shop.addItem(createVehicle("§cАвтомобиль", Material.IRON_BLOCK, "Современный автомобиль", 15000));
        shop.addItem(createVehicle("§cМотоцикл", Material.IRON_INGOT, "Быстрый мотоцикл", 8000));

        player.openInventory(shop);
    }

    private ItemStack createVehicle(String name, Material material, String description, int price) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.add("§7" + description);
            lore.add("§6Цена: §e$" + price);
            lore.add("§eПравый клик: купить");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    public void buyVehicle(Player player, String vehicleName, int price) {
        // TODO: Проверка баланса игрока
        // TODO: Снятие денег
        // TODO: Выдача транспортного средства (выход чего-то из яйца спавна или команда)
    }
}