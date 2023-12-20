package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EditRarity extends SubCommand {

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.rarity.edit")) {
            if (args.length == 3) {
                String name = args[0];
                String attribut = args[1];
                String value = args[2];

                if (!Var.getRarityHandler().existsRarity(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' does not exists!");
                    return;
                }

                if (attribut.equalsIgnoreCase("name")) {
                    if (Var.getRarityHandler().existsRarity(value)) {
                        player.sendMessage(ChatColor.RED + "A Rarity with the name '" + value + "' already exists");
                        return;
                    }
                    if (Var.getRarityTable().editRarityName(name, value)) {
                        Var.getRarityHandler().getRarityByName(name).setName(value);
                        player.sendMessage(ChatColor.GREEN + "Succesfully edited Rarity '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Could not update Rarity '" + name + "'");
                    }
                } else if (attribut.equalsIgnoreCase("prefix")) {
                    if (Var.getRarityTable().editRarityPrefix(name, value)) {
                        Var.getRarityHandler().getRarityByName(name).setPrefix(value);
                        player.sendMessage(ChatColor.GREEN + "Succesfully edited Rarity '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Could not update Rarity '" + name + "'");
                    }
                } else if (attribut.equalsIgnoreCase("percentage")) {
                    double percentage;
                    try {
                        percentage = Double.parseDouble(value);
                        if(percentage<0 || percentage>100)
                        {
                            player.sendMessage(ChatColor.RED + "Percentage must beetween 0 and 100!");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Please use a Number as Percentage");
                        return;
                    }
                    if (Var.getRarityTable().editRarityPercentage(name, percentage)) {
                        Var.getRarityHandler().getRarityByName(name).setPercentage(percentage);
                        player.sendMessage(ChatColor.GREEN + "Succesfully edited Rarity '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Could not update Rarity '" + name + "'");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Incorrect attribute please use one of the following attributes: '"
                            +ChatColor.GRAY + "name, prefix, percentage" + ChatColor.RED + "'!");
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
        return "edit";
    }

    @Override
    public String getDescription() {
        return "Edit a Rarity";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity edit <name> <attribute> <value>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[1];
        alias[0] = "ed";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if (args.length == 1) {
            return Var.getRarityHandler().getRarityNames();
        }

        if (args.length == 2) {
            ArrayList<String> attributes = new ArrayList<>();
            attributes.add("name");
            attributes.add("prefix");
            attributes.add("percentage");
            return attributes;
        }

        return new ArrayList<>();
    }
}
