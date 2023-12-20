package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.DungeonsPlugin;
import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class CreateDungeon extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("dungeons.dungeon.create")) {
            if (args.length == 3) {
                String name = args[0];
                String rarity = args[1];
                String schem = args[2];

                if (Var.getDungeonHandler().existsDungeon(name)) {
                    player.sendMessage(ChatColor.RED + "A Dungeon with the name '" + name + "' already exists!");
                    return;
                }
                if (!Var.getRarityHandler().existsRarity(rarity)) {
                    player.sendMessage(ChatColor.RED + "The rarity '" + rarity + "' does not exists!");
                    return;
                }
                File file;
                if (schem.endsWith(".schem")) {
                    file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics/" + schem);
                } else {
                    file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics/" + schem + ".schem");
                }
                if (!file.exists()) {
                    player.sendMessage(ChatColor.RED + "The schematic '" + schem + "' does not exists!");
                    return;
                }
                Rarity rarityObj = Var.getRarityHandler().getRarityByName(rarity);
                if(rarityObj == null){
                    player.sendMessage(ChatColor.RED + "Could not create Dungeon '" + name + "'!");
                    return;
                }
                int rarity_id = rarityObj.getId();
                int id = Var.getDungeonTable().createDungeon(name, rarity_id, schem);
                if (id != 0) {
                    Dungeon dungeon = new Dungeon(name, rarityObj, schem, id);
                    Var.getDungeonHandler().getDungeons().add(dungeon);
                    player.sendMessage(ChatColor.GREEN + "Dungeon '" + name + "' has been created!");
                } else {
                    player.sendMessage(ChatColor.RED + "Could not create Dungeon '" + name + "'!");
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
        return "create";
    }

    @Override
    public String getDescription() {
        return "Creates a Dungeon";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon create <name> <rarity> <schematic-name>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if (args.length == 2) {
            return Var.getRarityHandler().getRarityNames();
        }
        if (args.length == 3) {
            ArrayList<String> schematics = new ArrayList<>();
            File dir = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent() + "/WorldEdit/schematics");
            for(File file :  dir.listFiles())
            {
                schematics.add(file.getName());
            }
            return schematics;
        }

        return new ArrayList<>();
    }
}
