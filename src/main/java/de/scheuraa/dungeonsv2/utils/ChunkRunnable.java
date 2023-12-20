package de.scheuraa.dungeonsv2.utils;

import de.scheuraa.dungeonsv2.DungeonsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ChunkRunnable extends BukkitRunnable {

    private final Chunk chunk;


    public ChunkRunnable(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void run() {
        Random random = new Random();
        double zufZahl = random.nextDouble();

        double lowestChance = 1;

        Rarity toPasteRarity = null;
        //Iterator over all Rarities
        for (Rarity rarity : Var.getRarityHandler().getRarities()) {
            //Get the Percentage and divide it with 100 cause 1 means 1%
            double rarityPer = rarity.getPercentage() / 100;
            //If the Random number is lower then the Chance
            if (zufZahl <= rarityPer) {
                //If the Chance is lower than an previous Chance then pick this Rarity this makes that less Percentage is picked
                if (rarityPer <= lowestChance) {
                    toPasteRarity = rarity;
                    lowestChance = rarityPer;
                }
            }

        }

        if (toPasteRarity == null) {
            this.cancel();
            return;
        }
        Block b = chunk.getBlock(0, 5, 0);
        Biome biome = b.getBiome();
        if (Var.getOceanBiomes().contains(biome)) {
            this.cancel();
            return;
        }

        Dungeon dungeon = Var.getDungeonHandler().getRandomDungeonbyRarity(toPasteRarity, biome);
        if (dungeon == null) {
            this.cancel();
            return;
        }
        DungeonsPlugin plugin = DungeonsPlugin.getDungeonsPlugin();

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Block lowestLandBlock;
            int trys = 0;
            Location location = b.getLocation();
            Block probLowest = location.getWorld().getHighestBlockAt(location);
            while (true) {
                if (trys >= 60) {
                    probLowest = location.getWorld().getHighestBlockAt(location);
                    break;
                }
                if (Var.getTreeMaterials().contains(probLowest.getType())) {
                    probLowest = probLowest.getLocation().subtract(0, 1, 0).getBlock();
                } else {
                    break;
                }
            }
            lowestLandBlock = probLowest;
            Location pasteLoc = lowestLandBlock.getLocation();
            Var.getSchematicHandler().pasteSchematic(dungeon.getSchematicName(), pasteLoc);
        });

    }
}
