package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
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
                if (!Var.getRarityHandler().existsRarity(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' does not exist");
                    return;
                }
                if (Var.getDungeonHandler().isRarityInUse(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' can not be deleted cause it is used by an Dungeon!");
                    return;
                }

                if (Var.getRarityTable().deleteRarity(name)) {
                    Rarity rarity = Var.getRarityHandler().getRarityByName(name);
                    if (rarity != null) {
                        Var.getRarityHandler().getRarities().remove(rarity);
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
        return "/dungeons rarity remove <name>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[3];
        alias[0] = "rem";
        alias[1] = "delete";
        alias[2] = "del";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if (args.length == 1) {
            return Var.getRarityHandler().getRarityNames();
        }
        return new ArrayList<>();
    }
}
