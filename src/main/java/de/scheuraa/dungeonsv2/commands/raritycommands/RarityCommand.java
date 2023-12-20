package de.scheuraa.dungeonsv2.commands.raritycommands;

import de.scheuraa.dungeonsv2.commands.CommandManager;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class RarityCommand extends SubCommand {


    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public RarityCommand() {
        //Adde SubCommands von Rarity
        subCommands.add(new AddRarity());
        subCommands.add(new RemoveRarity());
        subCommands.add(new EditRarity());
        subCommands.add(new ListRarity());
        subCommands.add(new InfoRarity());
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length >= 1) {
            SubCommand target = this.get(args[0]);

            if (target == null) {
                player.sendMessage(getSyntax());
                return;
            }

            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(args));
            arrayList.remove(0);
            target.perform(player, arrayList.toArray(new String[0]));
        }else {
            player.sendMessage(getSyntax());
        }
    }

    @Override
    public String getName() {
        return "rarity";
    }

    @Override
    public String getDescription() {
        return "Rarity Command for Rarities";
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
        return CommandManager.getSubCommandStrings(args, this.subCommands);
    }


    private SubCommand get(String name) {
        for (SubCommand subCommand : this.subCommands) {
            if(CommandManager.checkSubCommandNameWithAlias(name, subCommand)!=null)
            {
                return subCommand;
            }
        }
        return null;
    }


}
