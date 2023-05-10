package de.scheuraa.dunegonsv2;

import de.scheuraa.dunegonsv2.commands.CommandManager;
import de.scheuraa.dunegonsv2.database.MySQLConnector;
import de.scheuraa.dunegonsv2.utils.Var;
import lombok.Getter;

@Getter
public class DungeonsLoader {

    private final DungeonsPlugin dungeonsPlugin;

    public DungeonsLoader(DungeonsPlugin dungeonsPlugin) {
        this.dungeonsPlugin = dungeonsPlugin;
    }

    public void init() {
        dungeonsPlugin.saveDefaultConfig();
        Var.initVar();
        new MySQLConnector();
        Var.getRarityTable().loadAllRarities();
        initCommands();
        initEvents();
    }

    public void initCommands() {
        CommandManager commandManager = new CommandManager();
        commandManager.setUp();
    }

    public void initEvents() {

    }

    public void unload() {

    }
}
