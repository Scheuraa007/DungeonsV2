package de.scheuraa.dunegonsv2.commands;

import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class RarityCommand extends SubCommand {


    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public RarityCommand() {
        //Adde SubCommands von Rarity
    }

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
    public ArrayList<String> getSubCommandArguments(String[] args) {

        return null;
    }


}
