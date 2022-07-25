package de.scheuraa.dunegonsv2.utils;

import de.scheuraa.dunegonsv2.DungeonsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ConfigManager {

    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private DungeonsPlugin plugin = DungeonsPlugin.getDungeonsPlugin();

    public ConfigManager() {
        saveDefaultConfig();
    }

    public void reloadConfig(){
        if(this.configFile ==null){
            this.configFile = new File(this.plugin.getDataFolder(),"config.yml");
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("config.yml");

        if(defaultStream !=null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(){
        return this.dataConfig;
    }

    public void saveConfig(){
        if(this.dataConfig ==null || this.configFile == null){
            try {
                this.getConfig().save(this.configFile);
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
            }
        }
    }

    public void saveDefaultConfig(){
        if(this.configFile ==null){
            this.configFile = new File(this.plugin.getDataFolder(),"config.yml");
        }
        if(!this.configFile.exists()){
            this.plugin.saveResource("config.yml",false);
        }
    }
}
