package de.scheuraa.dungeonsv2.utils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import de.scheuraa.dungeonsv2.DungeonsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SchematicHandler {


    public boolean pasteSchematic(String schemName, Location loc)
    {
        File file;
        if(schemName.endsWith(".schem")){
            file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent()+ "/WorldEdit/schematics/"+schemName);
        }else {
            file = new File(DungeonsPlugin.getDungeonsPlugin().getDataFolder().getParent()+ "/WorldEdit/schematics/"+schemName + ".schem");
        }
        if(!file.exists()){
            return false;
        }
        Clipboard clipboard;
        ClipboardFormat clipboardFormat = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = clipboardFormat.getReader(new FileInputStream(file))){
            clipboard = reader.read();
            World world = BukkitAdapter.adapt(loc.getWorld());
            try(EditSession editSession = WorldEdit.getInstance().newEditSession(world)){
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(loc.getX(),loc.getY(),loc.getZ()))
                        .build();
                Operations.complete(operation);
                Bukkit.getConsoleSender().sendMessage("Spawned Dungeon X=" + loc.getX() + " Y=" + loc.getY() + " Z=" + loc.getZ());
                return true;

            } catch (WorldEditException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
