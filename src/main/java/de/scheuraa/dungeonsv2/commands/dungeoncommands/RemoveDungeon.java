package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RemoveDungeon extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("dungeons.dungeon.remove")){
            if(args.length ==1){
                String name = args[0];
                if(!Var.getDungeonHandler().existsDungeon(name)){
                    player.sendMessage(ChatColor.RED + "The Dungeon '" + name + "' does not exists!");
                    return;
                }
                if(Var.getDungeonTable().deleteDungeon(name)){
                    Dungeon dungeon = Var.getDungeonHandler().getDungeonByName(name);
                    Var.getDungeonHandler().getDungeons().remove(dungeon);
                    player.sendMessage(ChatColor.GREEN + "Succesfull removed Dungeon '" + name + "'");
                }else {
                    player.sendMessage(ChatColor.RED + "Error while removing Dungeon '" + name + "'!");
                }
            }else {
                player.sendMessage(ChatColor.RED + "Incorrect Usage please use '" + getSyntax() + "'");
            }
        }else {
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Removes a Dungeon";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon remove <name>";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[3];
        alias[0] = "del";
        alias[1] = "delete";
        alias[2] = "rem";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if(args.length==1){
            return Var.getDungeonHandler().getDungeonNames();
        }
        return new ArrayList<>();
    }
}
