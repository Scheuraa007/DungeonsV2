package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InfoRarity extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("dungeons.rarity.info")){
            if(args.length==1){
                String name = args[0];
                if(!Var.getRarityHandler().existsRarity(name)){
                    player.sendMessage(ChatColor.RED + "The Rarity '" + name + "' does not exists!");
                    return;
                }
                Rarity rarity = Var.getRarityHandler().getRarityByName(name);
                player.sendMessage(ChatColor.GREEN + "Information about Rarity '" + ChatColor.translateAlternateColorCodes('&',rarity.getPrefix() + name)
                        + ChatColor.GREEN + "': ");
                player.sendMessage(ChatColor.GREEN + "    Prefix: " + ChatColor.GRAY + rarity.getPrefix());
                player.sendMessage(ChatColor.GREEN + "    Percentage: " + ChatColor.GRAY + rarity.getPercentage());
            }else{
                player.sendMessage(ChatColor.RED + "Incorrect Usage please use '" + getSyntax() + "'");
            }
        }else{
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Get informations about a Rarity";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity info <name>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[1];
        alias[0] = "i";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if(args.length ==1){
            return Var.getRarityHandler().getRarityNames();
        }
        return new ArrayList<>();
    }
}
