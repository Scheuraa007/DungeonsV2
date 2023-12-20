package de.scheuraa.dungeonsv2;

import de.scheuraa.dungeonsv2.commands.CommandManager;
import de.scheuraa.dungeonsv2.database.MySQLConnector;
import de.scheuraa.dungeonsv2.listeners.ChunkGenerating;
import de.scheuraa.dungeonsv2.utils.Var;
import lombok.Getter;
import org.bukkit.Bukkit;

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
        Var.getDungeonTable().loadAllDungeons();
        initCommands();
        initEvents();
    }

    public void initCommands() {
        CommandManager commandManager = new CommandManager();
        commandManager.setUp();
    }

    public void initEvents() {
        Bukkit.getPluginManager().registerEvents(new ChunkGenerating(), dungeonsPlugin);
    }

    public void unload() {

    }
}
