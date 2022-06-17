package de.scheuraa.dunegonsv2.commands;

import de.scheuraa.dunegonsv2.DungeonsPlugin;
import de.scheuraa.dunegonsv2.utils.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements TabExecutor {

    private final ArrayList<SubCommand> commands = new ArrayList<>();


    public CommandManager() {

    }

    public void setUp(){
        DungeonsPlugin.getDungeonsPlugin().getCommand("dungeons").setExecutor(this);
        this.commands.add(new RarityCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

       if(!(sender instanceof Player)){
           sender.sendMessage(ChatColor.RED + "Dieser Command ist nur für Spieler ausgelegt!");
           return true;
       }
       Player player = (Player) sender;

       if(command.getName().equalsIgnoreCase("dungeons")){
           if(args.length==0){
               sendInfo(player);
               return true;
           }
           SubCommand target = this.get(args[0]);

           if(target ==null){
               sendInfo(player);
               return true;
           }

           ArrayList<String> arrayList = new ArrayList<String>();

           arrayList.addAll(Arrays.asList(args));
           arrayList.remove(0);
           try{
               target.perform(player, args);
           }catch (Exception e){
               e.printStackTrace();
               sendInfo(player);
           }
       }
        return true;
    }

    private SubCommand get(String name){
        Iterator<SubCommand> subCommands = this.commands.iterator();

        while(subCommands.hasNext()){
            SubCommand subCommand = subCommands.next();
            if(subCommand.getName().equalsIgnoreCase(name)){
                return subCommand;
            }
            String[] aliases;
            int length = (aliases = subCommand.aliases()).length;

            for(int i=0;i<length;++i){
                String alias = aliases[i];
                if(name.equalsIgnoreCase(alias)){
                    return subCommand;
                }
            }
        }
        return null;
    }

    private void sendInfo(Player player){
        player.sendMessage(ChatColor.GREEN + "Welcome to DungeonsV2");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1){
            ArrayList<String> subCommandsArgs = new ArrayList<String>();
            for(int i = 0; i<commands.size();i++){
                subCommandsArgs.add(commands.get(i).getName());
            }
            return subCommandsArgs;
        }else if ((args.length == 2)){
            for(int i = 0; i<commands.size();i++){

            }
        }
        return null;
    }
}
