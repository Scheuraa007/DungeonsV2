package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InfoDungeon extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("dungeons.dungeon.info")){
            if(args.length==1){
                String name = args[0];
                if(!Var.getDungeonHandler().existsDungeon(name)){
                    player.sendMessage(ChatColor.RED + "The Dungeon '" + name + "' does not exists!");
                    return;
                }
                Dungeon dungeon = Var.getDungeonHandler().getDungeonByName(name);
                Rarity rarity = dungeon.getRarity();
                player.sendMessage(ChatColor.GREEN + "Information about Dungeon '" + name + "': ");
                player.sendMessage(ChatColor.GREEN + "    Rarity: " + ChatColor.translateAlternateColorCodes('&',
                        rarity.getPrefix() + rarity.getName()));
                player.sendMessage(ChatColor.GREEN + "    SchematicName: " + ChatColor.GRAY + dungeon.getSchematicName());
            }else{
                player.sendMessage(ChatColor.RED + "Incorrect Usage please use '" + getSyntax() + "'");
            }
        }else{
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Get informations about a Dungeon";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon info <name>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[1];
        alias[0] = "i";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if(args.length ==1){
            return Var.getDungeonHandler().getDungeonNames();
        }
        return new ArrayList<>();
    }
}
