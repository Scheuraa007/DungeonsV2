package de.scheuraa.dungeonsv2.listeners;

import de.scheuraa.dungeonsv2.DungeonsPlugin;
import de.scheuraa.dungeonsv2.utils.ChunkRunnable;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkGenerating implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event)
    {
        if(event.isNewChunk()){
            Chunk chunk = event.getChunk();
            try {
                ChunkRunnable chunkRunnable = new ChunkRunnable(chunk);
                chunkRunnable.runTaskAsynchronously(DungeonsPlugin.getDungeonsPlugin());
            }catch (RuntimeException e){
                e.printStackTrace();
            }

        }
    }
}
