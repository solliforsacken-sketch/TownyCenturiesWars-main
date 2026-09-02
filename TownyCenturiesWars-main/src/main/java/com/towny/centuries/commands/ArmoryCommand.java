package com.towny.centuries.commands;

import com.towny.centuries.managers.ArmoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmoryCommand implements CommandExecutor {

    private ArmoryManager armoryManager;

    public ArmoryCommand(ArmoryManager armoryManager) {
        this.armoryManager = armoryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда только для игроков!");
            return true;
        }

        Player player = (Player) sender;

        // Проверка прав доступа (должна быть эпоха Возрождения)
        if (!armoryManager.canAccessArmory("PlayerNation")) {
            player.sendMessage("§cЭтот функционал доступен только с эпохи Возрождения!");
            player.sendMessage("§eТекущее условие: ваша нация должна быть на уровне эпохи Возрождения или выше");
            return true;
        }

        armoryManager.openArmory(player);
        return true;
    }
}