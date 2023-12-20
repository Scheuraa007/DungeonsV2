package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.DungeonsPlugin;
import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.util.ArrayList;

public class EditDungeon extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.dungeon.edit")) {
            if (args.length == 3) {
                String name = args[0];
                String attribute = args[1];
                String value = args[2];

                if (!Var.getDungeonHandler().existsDungeon(name)) {
                    player.sendMessage(ChatColor.RED + "The Dungeon '" + name + "' does not exists!");
                    return;
                }
                if (attribute.equalsIgnoreCase("name")) {
                    if (Var.getDungeonHandler().existsDungeon(value)) {
                        player.sendMessage(ChatColor.RED + "A Dungeon with the name '" + value + "' already exists!");
                        return;
                    }
                    if (Var.getDungeonTable().updateDungeonName(name, value)) {
                        Var.getDungeonHandler().getDungeonByName(name).setName(value);
                        player.sendMessage(ChatColor.GREEN + "Succesfull edited Dungeon '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Error while editing Dungeon '" + name + "'!");
                    }
                } else if (attribute.equalsIgnoreCase("rarity")) {
                    if (!Var.getRarityHandler().existsRarity(value)) {
                        player.sendMessage(ChatColor.RED + "The Rarity '" + value + "' does not exists!");
                        return;
                    }
                    Rarity rarity = Var.getRarityHandler().getRarityByName(value);
                    int id = rarity.getId();
                    if (Var.getDungeonTable().updateDungeonRarity(name, id)) {
                        Var.getDungeonHandler().getDungeonByName(name).setRarity(rarity);
                        player.sendMessage(ChatColor.GREEN + "Succesfull edited Dungeon '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Error while editing Dungeon '" + name + "'!");
                    }
                } else if (attribute.equalsIgnoreCase("schem")) {
                    File file;
                    if (value.endsWith(".schem")) {
                        file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics/" + value);
                    } else {
                        file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics/" + value + ".schem");
                    }
                    if (!file.exists()) {
                        player.sendMessage(ChatColor.RED + "The schematic '" + value + "' does not exists!");
                        return;
                    }
                    if (Var.getDungeonTable().updateDungeonSchem(name, value)) {
                        Var.getDungeonHandler().getDungeonByName(name).setSchematicName(value);
                        player.sendMessage(ChatColor.GREEN + "Succesfull edited Dungeon '" + name + "'");
                    } else {
                        player.sendMessage(ChatColor.RED + "Error while editing Dungeon '" + name + "'!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect attribute please use one of the following attributes: '"
                            + ChatColor.GRAY + "name, rarity, schem" + ChatColor.RED + "'!");
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
        return "edit";
    }

    @Override
    public String getDescription() {
        return "Edits a dungeon";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon edit <name> <attribute> <value>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {

        if (args.length == 1) {
            return Var.getDungeonHandler().getDungeonNames();
        } else if (args.length == 2) {
            ArrayList<String> attributes = new ArrayList<>();
            attributes.add("name");
            attributes.add("rarity");
            attributes.add("schem");
            return attributes;
        } else if (args.length == 3) {
            if(args[1].equalsIgnoreCase("rarity")){
                return Var.getRarityHandler().getRarityNames();
            }else if(args[1].equalsIgnoreCase("schem")){
                ArrayList<String> schematics = new ArrayList<>();
                File dir = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics");
                for(File file :  dir.listFiles())
                {
                    schematics.add(file.getName());
                }
                return schematics;
            }
        }
        return new ArrayList<>();
    }
}
