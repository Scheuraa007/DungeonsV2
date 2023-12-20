package de.scheuraa.dungeonsv2.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dungeonsv2.database.DungeonTable;
import de.scheuraa.dungeonsv2.database.RarityTable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

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
    private static RarityHandler rarityHandler;
    @Getter
    private static DungeonHandler dungeonHandler;
    @Getter
    private static DungeonTable dungeonTable;
    @Getter
    private static SchematicHandler schematicHandler;
    @Getter
    private static ArrayList<Biome> oceanBiomes;
    @Getter
    private static ArrayList<Material> treeMaterials;
    public static void initVar() {
        configManager = new ConfigManager();
        configManager.reloadConfig();
        rarityTable = new RarityTable();
        dungeonTable = new DungeonTable();
        rarityHandler = new RarityHandler();
        dungeonHandler = new DungeonHandler();
        schematicHandler = new SchematicHandler();
        oceanBiomes = new ArrayList<>();
        treeMaterials = new ArrayList<>();
        fillOceanBiomes();
        fillTreeMaterials();
    }

    private static void fillOceanBiomes(){
        oceanBiomes.add(Biome.OCEAN);
        oceanBiomes.add(Biome.COLD_OCEAN);
        oceanBiomes.add(Biome.DEEP_OCEAN);
        oceanBiomes.add(Biome.FROZEN_OCEAN);
        oceanBiomes.add(Biome.LUKEWARM_OCEAN);
        oceanBiomes.add(Biome.WARM_OCEAN);
        oceanBiomes.add(Biome.DEEP_LUKEWARM_OCEAN);
        oceanBiomes.add(Biome.DEEP_COLD_OCEAN);
        oceanBiomes.add(Biome.DEEP_FROZEN_OCEAN);
        oceanBiomes.add(Biome.RIVER);
        oceanBiomes.add(Biome.FROZEN_RIVER);
    }

    private static void fillTreeMaterials(){
        treeMaterials.add(Material.ACACIA_LOG);
        treeMaterials.add(Material.ACACIA_LEAVES);
        treeMaterials.add(Material.OAK_LOG);
        treeMaterials.add(Material.OAK_LEAVES);
        treeMaterials.add(Material.BIRCH_LOG);
        treeMaterials.add(Material.BIRCH_LEAVES);
        treeMaterials.add(Material.JUNGLE_LOG);
        treeMaterials.add(Material.JUNGLE_LEAVES);
        treeMaterials.add(Material.DARK_OAK_LOG);
        treeMaterials.add(Material.DARK_OAK_LEAVES);
        treeMaterials.add(Material.MANGROVE_LOG);
        treeMaterials.add(Material.MANGROVE_LEAVES);
        treeMaterials.add(Material.SPRUCE_LOG);
        treeMaterials.add(Material.SPRUCE_LEAVES);
    }

}
