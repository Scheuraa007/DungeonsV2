package de.scheuraa.dunegonsv2;

import de.scheuraa.dunegonsv2.commands.CommandManager;
import lombok.Getter;

@Getter
public class DungeonsLoader {

    private final DungeonsPlugin dungeonsPlugin;

    public DungeonsLoader(DungeonsPlugin dungeonsPlugin) {
        this.dungeonsPlugin = dungeonsPlugin;
    }

    public void init(){
        initCommands();
        initEvents();
    }

    public void initCommands(){
        CommandManager commandManager = new CommandManager();
        commandManager.setUp();
    }

    public void initEvents(){

    }

    public void unload(){

    }
}
