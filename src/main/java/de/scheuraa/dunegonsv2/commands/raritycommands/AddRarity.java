package de.scheuraa.dunegonsv2.commands.raritycommands;

import de.scheuraa.dunegonsv2.utils.Rarity;
import de.scheuraa.dunegonsv2.utils.SubCommand;
import de.scheuraa.dunegonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AddRarity extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.rarity.add")) {
            if (args.length == 3) {
                String name = args[0];
                String prefix = args[1];
                double percentage = Double.parseDouble(args[2]);
                if (Var.getRarityTable().existsRarity(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' already exists!");
                    return;
                }
                if (Var.getRarityTable().createRarity(name, prefix, percentage)) {
                    Rarity rarity = new Rarity(name, prefix, percentage);
                    Var.getRarities().add(rarity);
                    player.sendMessage(ChatColor.GREEN + "Rarity '" + name + "' has been created!");
                } else {
                    player.sendMessage(ChatColor.RED + "Could not create Rarity '" + name + "' please check if the Rarity already exists!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Incorrect Usage please use '" + getSyntax() + "'");
            }
        } else {
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Adds a Rarity";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity add <Name> <Prefix> <Percentage>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        return null;
    }
}
