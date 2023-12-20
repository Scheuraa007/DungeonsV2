package de.scheuraa.dungeonsv2.utils;

import lombok.Getter;
import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.Random;

public class DungeonHandler {

    @Getter
    private ArrayList<Dungeon> dungeons;

    public DungeonHandler() {
        this.dungeons = new ArrayList<>();
    }

    public boolean existsDungeon(String name)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean existsDungeon(int id)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getId() == id){
                return true;
            }
        }
        return false;
    }

    public Dungeon getDungeonByName(String name)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getName().equalsIgnoreCase(name)){
                return dungeon;
            }
        }
        return null;
    }

    public Dungeon getDungeonByID(int id)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getId() == id){
                return dungeon;
            }
        }
        return null;
    }

    public boolean isRarityInUse(String name)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getRarity().getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isRarityInUse(int id)
    {
        for(Dungeon dungeon : dungeons)
        {
            if(dungeon.getRarity().getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getDungeonNames(){
        ArrayList<String> dungeonNames = new ArrayList<>();
        for (Dungeon dungeon : dungeons){
            dungeonNames.add(dungeon.getName());
        }
        return dungeonNames;
    }

    public Dungeon getRandomDungeonbyRarity(Rarity rarity, Biome biome){
        ArrayList<Dungeon> dungeonsWithRarity = new ArrayList<>();
        boolean isBiome = false;

        for(Dungeon dungeon : dungeons) {
            if (dungeon.getRarity().equals(rarity)) {
                //If biome matches
                if (dungeon.getBiomeList().contains(biome)) {
                    //When no biome before matched then clear the list and add the Dungeon. When one or more Dungeons match the Biom
                    // they should get priority to spawn in that Biome
                    if (!isBiome) {
                        isBiome = true;
                        dungeonsWithRarity.clear();
                        dungeonsWithRarity.add(dungeon);
                    } else {
                        dungeonsWithRarity.add(dungeon);
                    }
                    //Only add a Dungeon whose Biom doesn´t match when none Biome matched earlier
                } else {
                    if (!isBiome) {
                        dungeonsWithRarity.add(dungeon);
                    }
                }
            }
        }
        if (dungeonsWithRarity.size() <= 0) {
            return null;
        }

        Random random = new Random();
        int zufZahl = random.nextInt(dungeonsWithRarity.size());
        return dungeonsWithRarity.get(zufZahl);
    }


}
