package de.scheuraa.dungeonsv2.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rarity {

    private String name, prefix;
    int id;
    private double percentage;

    public Rarity(String name, String prefix, double percentage, int id) {
        this.name = name;
        this.prefix = prefix;
        this.percentage = percentage;
        this.id = id;
    }
}
