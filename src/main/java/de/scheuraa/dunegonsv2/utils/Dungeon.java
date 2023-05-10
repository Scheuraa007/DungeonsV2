package de.scheuraa.dunegonsv2.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Biome;

@Getter
@Setter
public class Dungeon {

    private String name;

    private Rarity rarity;

    private Biome biome;

    private String schematicName;
}
