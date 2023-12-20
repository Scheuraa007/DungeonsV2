package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListRarity extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.rarity.list")) {
            String rarityNames = "";
            ArrayList<Rarity> rarities = Var.getRarityHandler().getRarities();
            for (int i=0; i<rarities.size()-1;i++) {
                Rarity rarity = rarities.get(i);
                rarityNames = rarityNames + ChatColor.translateAlternateColorCodes('&', rarity.getPrefix() + rarity.getName())
                        + ChatColor.GRAY+ ", ";
            }
            Rarity last = rarities.get(rarities.size()-1);
            rarityNames = rarityNames + ChatColor.translateAlternateColorCodes('&', last.getPrefix() + last.getName());
            player.sendMessage(ChatColor.GREEN + "Currently the following Rarities are existing: " + rarityNames);
        } else {
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Creates a list of all rarities";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity list";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[2];
        alias[0] = "li";
        alias[1] = "l";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        return new ArrayList<>();
    }
}
