package de.scheuraa.dunegonsv2.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dunegonsv2.database.RarityTable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class Var {

    @Getter
    private static ConfigManager configManager;
    @Getter
    @Setter
    private static MysqlDataSource dataSource;
    @Getter
    private static RarityTable rarityTable;
    @Getter
    private static final String noPerm = ChatColor.RED + "You don´t have the permission to perform that command!";
    @Getter
    private static ArrayList<Rarity> rarities;

    public static void initVar() {
        configManager = new ConfigManager();
        configManager.reloadConfig();
        rarityTable = new RarityTable();
        rarities = new ArrayList<>();
    }

    public static Rarity getRarityByName(String name) {
        for (Rarity rarity : rarities) {
            if (rarity.getName().equalsIgnoreCase(name)) {
                return rarity;
            }
        }
        return null;
    }
}
