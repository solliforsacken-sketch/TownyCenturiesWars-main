package com.towny.centuries.commands;

import com.towny.centuries.managers.CenturyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CenturyCommand implements CommandExecutor {

    private CenturyManager centuryManager;

    public CenturyCommand(CenturyManager centuryManager) {
        this.centuryManager = centuryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда только для игроков!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            centuryManager.openCenturyMenu(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            player.sendMessage("§6=== Информация об эпохах ===");
            player.sendMessage("§eПримитивный век§7 - начало игры");
            player.sendMessage("§eКаменный век§7 - деревянные и каменные инструменты");
            player.sendMessage("§eБронзовый век§7 - золотые доспехи и инструменты");
            player.sendMessage("§eЖелезный век§7 - железные доспехи и инструменты");
            player.sendMessage("§eРасцвет§7 - алмазные доспехи, тракторы и поезда");
            player.sendMessage("§eРаннее средневековье§7 - кузнец на спавне");
            player.sendMessage("§eРазвитое Средневековье§7 - чародейство (требует нации)");
            player.sendMessage("§eПозднее средневековье§7 - незерит и динамит (требует нации)");
            player.sendMessage("§eЭпоха возрождения§7 - оружейная и амуниция (требует нации)");
            player.sendMessage("§eАтомный век§7 - современное оружие (требует нации)");
            return true;
        }

        player.sendMessage("§cНеизвестная команда! Используйте: /century или /century info");
        return false;
    }
}