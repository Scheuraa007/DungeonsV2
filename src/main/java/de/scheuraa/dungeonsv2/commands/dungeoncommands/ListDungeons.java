package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListDungeons extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("dungeons.dungeon.list")){
            String dungeonNames = "";
            ArrayList<Dungeon> dungeons = Var.getDungeonHandler().getDungeons();
            for(int i =0; i<dungeons.size()-1;i++){
                Dungeon dungeon = dungeons.get(i);
                dungeonNames = dungeonNames + dungeon.getName() + ", ";
            }
            dungeonNames = dungeonNames + dungeons.get(dungeons.size()-1).getName();
            player.sendMessage(ChatColor.GREEN + "Currently the following Dungeons are existing: " + ChatColor.GRAY + dungeonNames);

        }else {
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Lists all Dungeons";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon list";
    }

    @Override
    public String[] aliases() {
        String[] alias = new String[2];
        alias[0] = "l";
        alias[1] = "li";
        return alias;
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        return new ArrayList<>();
    }
}
