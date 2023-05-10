package de.scheuraa.dunegonsv2;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonsPlugin extends JavaPlugin {

    @Getter
    private static DungeonsPlugin dungeonsPlugin;
    private DungeonsLoader loader;

    @Override
    public void onEnable() {
        dungeonsPlugin = this;
        loader = new DungeonsLoader(this);
        this.loader.init();
    }

    @Override
    public void onDisable() {
        this.loader.unload();
    }
}
