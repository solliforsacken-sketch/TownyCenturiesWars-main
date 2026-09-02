package com.towny.centuries;

import com.towny.centuries.commands.*;
import com.towny.centuries.listeners.*;
import com.towny.centuries.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyCenturiesWarsPlugin extends JavaPlugin {

    private CenturyManager centuryManager;
    private WarsManager warsManager;
    private ArmoryManager armoryManager;
    private VehicleManager vehicleManager;

    @Override
    public void onEnable() {
        getLogger().info("=== TownyCenturiesWars Plugin Loading ===");

        // Сохраняем конфиги
        saveDefaultConfig();

        // Инициализируем менеджеры
        this.centuryManager = new CenturyManager(this);
        this.warsManager = new WarsManager(this);
        this.armoryManager = new ArmoryManager(this);
        this.vehicleManager = new VehicleManager(this);

        // Регистрируем команды
        registerCommands();

        // Регистрируем слушатели
        registerListeners();

        getLogger().info("✓ TownyCenturiesWars Plugin успешно загружен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TownyCenturiesWars Plugin отключен");
    }

    private void registerCommands() {
        getCommand("century").setExecutor(new CenturyCommand(centuryManager));
        getCommand("wars").setExecutor(new WarsCommand(warsManager));
        getCommand("armory").setExecutor(new ArmoryCommand(armoryManager));
        getCommand("veh").setExecutor(new VehicleCommand(vehicleManager));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CenturyListener(centuryManager), this);
        getServer().getPluginManager().registerEvents(new WarsListener(warsManager), this);
        getServer().getPluginManager().registerEvents(new ArmoryListener(armoryManager), this);
    }

    public CenturyManager getCenturyManager() {
        return centuryManager;
    }

    public WarsManager getWarsManager() {
        return warsManager;
    }

    public ArmoryManager getArmoryManager() {
        return armoryManager;
    }

    public VehicleManager getVehicleManager() {
        return vehicleManager;
    }
}