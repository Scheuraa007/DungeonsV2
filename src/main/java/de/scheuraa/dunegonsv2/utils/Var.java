package de.scheuraa.dunegonsv2.utils;

import de.scheuraa.dunegonsv2.DungeonsPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class Var {

    @Getter
    private static ConfigManager configManager;

    public Var() {
        configManager = new ConfigManager();
    }
}
