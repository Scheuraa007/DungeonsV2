package de.scheuraa.dunegonsv2.commands;

import de.scheuraa.dunegonsv2.DungeonsPlugin;
import de.scheuraa.dunegonsv2.commands.raritycommands.RarityCommand;
import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandManager implements TabExecutor {

    private final ArrayList<SubCommand> commands = new ArrayList<>();


    public CommandManager() {

    }

    public void setUp() {
        Objects.requireNonNull(DungeonsPlugin.getDungeonsPlugin().getCommand("dungeons")).setExecutor(this);
        this.commands.add(new RarityCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dieser Command ist nur für Spieler ausgelegt!");
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("dungeons")) {
            if (args.length == 0) {
                sendInfo(player);
                return true;
            }
            SubCommand target = this.get(args[0]);

            if (target == null) {
                sendInfo(player);
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(args));
            arrayList.remove(0);
            try {
                target.perform(player, arrayList.toArray(new String[0]));
            } catch (Exception e) {
                e.printStackTrace();
                sendInfo(player);
            }
        }
        return true;
    }

    private SubCommand get(String name) {
        for (SubCommand subCommand : this.commands) {
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

    private void sendInfo(Player player) {
        player.sendMessage(ChatColor.GREEN + "Welcome to DungeonsV2");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 1) {
            ArrayList<String> subCommandsArgs = new ArrayList<>();
            for (SubCommand subCommand : commands) {
                subCommandsArgs.add(subCommand.getName());
            }
            return subCommandsArgs;
        } else if ((args.length >= 2)) {
            for (SubCommand subCommand : commands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(args));
                    arrayList.remove(0);

                    return subCommand.getSubCommandArguments(arrayList.toArray(new String[0]));
                }
            }
        }
        return null;
    }
}
