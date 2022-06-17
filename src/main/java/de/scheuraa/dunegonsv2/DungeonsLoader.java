package de.scheuraa.dunegonsv2;

import lombok.Getter;

@Getter
public class DungeonsLoader {

    private final DungeonsPlugin dungeonsPlugin;

    public DungeonsLoader(DungeonsPlugin dungeonsPlugin) {
        this.dungeonsPlugin = dungeonsPlugin;
    }

    public void init(){
        initCommands();
        initEvents();
    }

    public void initCommands(){

    }

    public void initEvents(){

    }

    public void unload(){

    }
}
