package de.scheuraa.dunegonsv2.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class SubCommand {

    public SubCommand() {

    }


    public abstract void perform(Player player, String[] args);

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String[] aliases();

    public abstract ArrayList<String> getSubCommandArguemnts(Player player, String[] args);
}
