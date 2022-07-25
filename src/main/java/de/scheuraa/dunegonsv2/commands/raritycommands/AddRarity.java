package de.scheuraa.dunegonsv2.commands.raritycommands;

import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AddRarity extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Fügt eine Rarity hinzu";
    }

    @Override
    public String getSyntax() {
        return "/dungeons rarity add";
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
