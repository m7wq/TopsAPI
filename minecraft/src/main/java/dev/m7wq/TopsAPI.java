package dev.m7wq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import dev.m7wq.entity.HologramsContainer;
import lombok.Getter;

@Getter
public class TopsAPI 
{
    private TopsConfig config;
    private List<HologramsContainer<?, ?>> containers = new ArrayList<>();
    private static TopsAPI instance;

    private TopsAPI(Plugin plugin){
        config = new TopsConfig(
            "&8-------- LEADERBOARD --------", 
            "&8#%rank%. &e%name% &7(&f%amount%&7)", 
            "&8-------- LEADERBOARD --------",
            0.25,
            200
        );

        startUpdater(plugin);
    }


    public static TopsAPI load(Plugin plugin){
        instance = new TopsAPI(plugin);
        return instance;
    }

    public void appendContainer(HologramsContainer<?, ?> container){
        containers.add(container);
    }

    private void startUpdater(Plugin plugin){
        new BukkitRunnable() {

            @Override
            public void run() {
                containers.forEach(container->{
                    if (container.getLocation().getWorld()==null) {
                        return;
                    }
                    container.clear();
                    container.display();
                });
            }
            
        }.runTaskTimer(plugin, 0, config.getRefreshInterval());
    }

    public static TopsAPI getInstance(){
        
        if (instance==null) 
            throw new IllegalStateException("Please load the TopsAPI using MinecraftTopAPI#load()");
        

        return instance;
    }





}
