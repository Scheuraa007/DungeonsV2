package de.scheuraa.dungeonsv2.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Biome;

import java.util.ArrayList;

@Getter
@Setter
public class Dungeon {

    private String name;

    private Rarity rarity;

    private ArrayList<Biome> biomeList;

    private String schematicName;

    private int id;

    public Dungeon(String name, Rarity rarity, String schematicName, int id) {
        this.name = name;
        this.rarity = rarity;
        this.schematicName = schematicName;
        this.id = id;
        biomeList = new ArrayList<>();
    }
}
