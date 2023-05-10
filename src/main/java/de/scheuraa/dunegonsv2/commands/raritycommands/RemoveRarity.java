package de.scheuraa.dunegonsv2.commands.raritycommands;

import de.scheuraa.dunegonsv2.utils.Rarity;
import de.scheuraa.dunegonsv2.utils.SubCommand;
import de.scheuraa.dunegonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RemoveRarity extends SubCommand {

    public RemoveRarity() {
        //Adde SubCommands
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.rarity.remove")) {
            if (args.length == 1) {
                String name = args[0];
                if (!Var.getRarityTable().existsRarity(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' does not exist");
                    return;
                }
                if (Var.getRarityTable().deleteRarity(name)) {
                    Rarity rarity = Var.getRarityByName(name);
                    if (rarity != null) {
                        Var.getRarities().remove(rarity);
                    }
                    player.sendMessage(ChatColor.GREEN + "Succesfull removed Rarity '" + name + "'.");
                } else {
                    player.sendMessage(ChatColor.RED + "Could not remove Rarity '" + name + "'!");
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
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Removes a Rarity";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity remove <Name>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[1];
        alias[0] = "rem";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        ArrayList<String> rarities = new ArrayList<>();
        for (Rarity rarity : Var.getRarities()) {
            rarities.add(rarity.getName());
        }
        return rarities;
    }
}
