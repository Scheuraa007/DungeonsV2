package de.scheuraa.dungeonsv2.commands.dungeoncommands;

import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.SubCommand;
import de.scheuraa.dungeonsv2.utils.Var;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PasteDungeon extends SubCommand {
    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("dungeons.dungeon.paste")){
            if(args.length ==1)
            {
                String name = args[0];

                if(!Var.getDungeonHandler().existsDungeon(name)){
                    player.sendMessage(ChatColor.RED + "The Dungeon '" + name + "' does not exists!");
                    return;
                }
                Dungeon dungeon = Var.getDungeonHandler().getDungeonByName(name);
                boolean suc = Var.getSchematicHandler().pasteSchematic(dungeon.getSchematicName(),player.getLocation());
                if(suc)
                {
                    player.sendMessage(ChatColor.GREEN + "Successful pasted Dungeon '" + name + "'");
                    return;
                }
                player.sendMessage(ChatColor.RED + "Error while pasting Dungeon '" + name + "' please read the Console");
            }else {
                player.sendMessage(ChatColor.RED + "Incorrect Usage please use '" + getSyntax() + "'");
            }
        }else {
            player.sendMessage(Var.getNoPerm());
        }
    }

    @Override
    public String getName() {
        return "paste";
    }

    @Override
    public String getDescription() {
        return "Pastes a Dungeon on the Player location";
    }

    @Override
    public String getSyntax() {
        return "/dungeons dungeon paste <name>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public ArrayList<String> getSubCommandArguments(String[] args) {
        if(args.length == 1){
            return Var.getDungeonHandler().getDungeonNames();
        }
        return new ArrayList<>();
    }
}
