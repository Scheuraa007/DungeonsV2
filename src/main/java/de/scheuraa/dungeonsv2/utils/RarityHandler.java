package de.scheuraa.dungeonsv2.utils;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class RarityHandler {
    @Getter
    private ArrayList<Rarity> rarities;

    public RarityHandler() {
        rarities = new ArrayList<>();
    }

    public Rarity getRarityByName(String name) {
        for (Rarity rarity : rarities) {
            Bukkit.getConsoleSender().sendMessage(rarity.getName() + " " + name);
            if (rarity.getName().equalsIgnoreCase(name)) {
                return rarity;
            }
        }
        return null;
    }

    public Rarity getRarityById(int id){
        for (Rarity rarity : rarities) {
            if (rarity.getId() == id) {
                return rarity;
            }
        }
        return null;
    }

    public boolean existsRarity(String name)
    {
        for(Rarity rarity : rarities)
        {
            if(rarity.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean existsRarity(int id)
    {
        for(Rarity rarity : rarities)
        {
            if(rarity.getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getRarityNames(){
        ArrayList<String> rarityNames = new ArrayList<>();
        for(Rarity rarity : rarities){
            rarityNames.add(rarity.getName());
        }
        return rarityNames;
    }
}
