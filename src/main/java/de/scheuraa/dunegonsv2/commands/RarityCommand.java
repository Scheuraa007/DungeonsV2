package de.scheuraa.dunegonsv2.commands;

import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RarityCommand extends SubCommand {


    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public String getName() {
        return "rarity";
    }

    @Override
    public String getDescription() {
        return "Rarity Command for Raritys";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguemnts(Player player, String[] args) {
        return null;
    }


}
