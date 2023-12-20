package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
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
                double percentage;
                try {
                    percentage = Double.parseDouble(args[2]);
                    if(percentage<0 || percentage>100)
                    {
                        player.sendMessage(ChatColor.RED + "Percentage must beetween 0 and 100!");
                        return;
                    }
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "Please use a number as percentage value");
                    return;
                }
                if (Var.getRarityHandler().existsRarity(name)) {
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' already exists!");
                    return;
                }
                int id = Var.getRarityTable().createRarity(name, prefix, percentage);
                if (id!=0) {
                    Rarity rarity = new Rarity(name, prefix, percentage,id);
                    Var.getRarityHandler().getRarities().add(rarity);
                    player.sendMessage(ChatColor.GREEN + "Rarity '" + name + "' has been created!");
                } else {
                    player.sendMessage(ChatColor.RED + "Could not create Rarity '" + name + "'!");
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
        return "/dungeons rarity add <name> <prefix> <percentage>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        return new ArrayList<>();
    }
}
