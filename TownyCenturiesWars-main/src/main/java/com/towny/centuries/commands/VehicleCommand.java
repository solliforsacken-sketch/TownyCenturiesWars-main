package com.towny.centuries.commands;

import com.towny.centuries.managers.VehicleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VehicleCommand implements CommandExecutor {

    private VehicleManager vehicleManager;

    public VehicleCommand(VehicleManager vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда только для игроков!");
            return true;
        }

        Player player = (Player) sender;

        // Проверка условия - требует эпохи Расцвета или выше
        vehicleManager.openVehicleShop(player);
        player.sendMessage("§6Магазин транспорта открыт!");
        return true;
    }
}