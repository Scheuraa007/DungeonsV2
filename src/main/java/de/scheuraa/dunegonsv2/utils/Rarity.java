package de.scheuraa.dunegonsv2.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rarity {

    private String name, prefix;
    private double percentage;

    public Rarity(String name, String prefix, double percentage) {
        this.name = name;
        this.prefix = prefix;
        this.percentage = percentage;
    }
}
