package com.towny.centuries.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

public class Century {

    public enum CenturyType {
        PRIMITIVE("Примитивный век", 0),
        STONE("Каменный век", 1),
        BRONZE("Бронзовый век", 2),
        IRON("Железный век", 3),
        FLOURISH("Расцвет", 4),
        EARLY_MEDIEVAL("Раннее средневековье", 5),
        MEDIEVAL("Развитое Средневековье", 6),
        LATE_MEDIEVAL("Позднее средневековье", 7),
        RENAISSANCE("Эпоха возрождения", 8),
        ATOMIC("Атомный век", 9);

        private final String displayName;
        private final int level;

        CenturyType(String displayName, int level) {
            this.displayName = displayName;
            this.level = level;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getLevel() {
            return level;
        }
    }

    private CenturyType type;
    private Map<Material, Integer> requiredItems;
    private boolean unlocked;

    public Century(CenturyType type) {
        this.type = type;
        this.requiredItems = new HashMap<>();
        this.unlocked = false;
        loadRequirements();
    }

    private void loadRequirements() {
        switch (type) {
            case PRIMITIVE:
                this.unlocked = true;
                break;
            case STONE:
                addRequirement(Material.STICK, 32);
                addRequirement(Material.WHEAT, 16);
                addRequirement(Material.COBBLESTONE, 64);
                addRequirement(Material.GRAVEL, 32);
                addRequirement(Material.DIRT, 32);
                addRequirement(Material.COARSE_DIRT, 32);
                addRequirement(Material.SAND, 32);
                addRequirement(Material.BAMBOO, 64);
                break;
            case BRONZE:
                addRequirement(Material.STICK, 128);
                addRequirement(Material.OAK_LOG, 64);
                addRequirement(Material.SWEET_BERRIES, 16);
                addRequirement(Material.COBBLESTONE, 64);
                addRequirement(Material.ANDESITE, 64);
                addRequirement(Material.DIORITE, 64);
                addRequirement(Material.GRANITE, 64);
                addRequirement(Material.COAL, 32);
                addRequirement(Material.CRAFTING_TABLE, 16);
                addRequirement(Material.FURNACE, 8);
                addRequirement(Material.RED_TULIP, 16);
                addRequirement(Material.ORANGE_TULIP, 32);
                break;
            case IRON:
                addRequirement(Material.OAK_LOG, 64);
                addRequirement(Material.SPRUCE_LOG, 64);
                addRequirement(Material.WHEAT, 256);
                addRequirement(Material.SWEET_BERRIES, 128);
                addRequirement(Material.ANDESITE, 256);
                addRequirement(Material.STONE, 256);
                addRequirement(Material.GRAVEL, 256);
                addRequirement(Material.SAND, 256);
                addRequirement(Material.IRON_INGOT, 48);
                addRequirement(Material.COAL, 64);
                addRequirement(Material.PUMPKIN, 24);
                addRequirement(Material.MELON, 24);
                addRequirement(Material.POPPY, 64);
                break;
            case FLOURISH:
                addRequirement(Material.JUNGLE_LOG, 96);
                addRequirement(Material.OAK_LOG, 96);
                addRequirement(Material.SUGAR_CANE, 128);
                addRequirement(Material.SWEET_BERRIES, 128);
                addRequirement(Material.COOKED_SALMON, 16);
                addRequirement(Material.COOKED_COD, 16);
                addRequirement(Material.BREAD, 128);
                addRequirement(Material.GRAVEL, 256);
                addRequirement(Material.ANDESITE, 256);
                addRequirement(Material.DIORITE, 256);
                addRequirement(Material.GRANITE, 256);
                addRequirement(Material.EMERALD, 16);
                addRequirement(Material.IRON_INGOT, 48);
                addRequirement(Material.DIAMOND, 16);
                addRequirement(Material.GOLD_INGOT, 32);
                addRequirement(Material.LAPIS_LAZULI, 64);
                addRequirement(Material.REDSTONE, 64);
                addRequirement(Material.CHARCOAL, 128);
                addRequirement(Material.CORNFLOWER, 64);
                addRequirement(Material.BROWN_MUSHROOM, 128);
                break;
            // Остальные века добавить по аналогии
        }
    }

    private void addRequirement(Material material, int amount) {
        requiredItems.put(material, amount);
    }

    public CenturyType getType() {
        return type;
    }

    public Map<Material, Integer> getRequiredItems() {
        return requiredItems;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public String getDisplayName() {
        return type.getDisplayName();
    }

    public int getLevel() {
        return type.getLevel();
    }
}