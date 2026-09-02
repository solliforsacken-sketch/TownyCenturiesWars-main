package com.towny.centuries.commands;

import com.towny.centuries.managers.WarsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarsCommand implements CommandExecutor {

    private WarsManager warsManager;

    public WarsCommand(WarsManager warsManager) {
        this.warsManager = warsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда только для игроков!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§c/wars declare <нация1> <нация2> [длительность]");
            player.sendMessage("§c/wars list");
            player.sendMessage("§c/wars status <нация>");
            return true;
        }

        if (args[0].equalsIgnoreCase("declare")) {
            if (args.length < 3) {
                player.sendMessage("§cИспользуй: /wars declare <нация1> <нация2> [длительность]");
                return true;
            }

            String attacker = args[1];
            String defender = args[2];
            int duration = args.length > 3 ? Integer.parseInt(args[3]) : 60; // 60 минут по умолчанию

            if (warsManager.declareWar(attacker, defender, duration)) {
                player.sendMessage("§a✓ Война объявлена: §c" + attacker + " §7vs §b" + defender);
            } else {
                player.sendMessage("§cВойна между этими нациями уже идёт!");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            player.sendMessage("§6=== Активные войны ===");
            // TODO: Показать список активных войн
            player.sendMessage("§eВойн не найдено");
            return true;
        }

        if (args[0].equalsIgnoreCase("status")) {
            if (args.length < 2) {
                player.sendMessage("§cИспользуй: /wars status <нация>");
                return true;
            }
            // TODO: Показать статус войн нации
            player.sendMessage("§eВойн для нации §c" + args[1] + " §eне найдено");
            return true;
        }

        player.sendMessage("§cНеизвестная подкоманда!");
        return false;
    }
}