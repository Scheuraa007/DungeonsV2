package de.scheuraa.dunegonsv2.commands.raritycommands;

import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class RarityCommand extends SubCommand {


    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public RarityCommand() {
        //Adde SubCommands von Rarity
        subCommands.add(new AddRarity());
        subCommands.add(new RemoveRarity());
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
        if (args.length == 1) {
            ArrayList<String> subCommandsArgs = new ArrayList<>();
            for (SubCommand subCommand : subCommands) {
                subCommandsArgs.add(subCommand.getName());
            }
            return subCommandsArgs;
        } else if ((args.length >= 2)) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(args));
                    arrayList.remove(0);

                    return subCommand.getSubCommandArguments(arrayList.toArray(new String[0]));
                }
            }
        }
        return null;
    }

    private SubCommand get(String name) {
        for (SubCommand subCommand : this.subCommands) {
            if (subCommand.getName().equalsIgnoreCase(name)) {
                return subCommand;
            }
            String[] aliases;
            int length = (aliases = subCommand.aliases()).length;

            for (int i = 0; i < length; ++i) {
                String alias = aliases[i];
                if (name.equalsIgnoreCase(alias)) {
                    return subCommand;
                }
            }
        }
        return null;
    }


}
