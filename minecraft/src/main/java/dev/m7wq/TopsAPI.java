package dev.m7wq;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import dev.m7wq.configs.TopsConfig;
import dev.m7wq.entity.holograms.HologramsContainer;
import dev.m7wq.entity.process.SortingProcessor;
import lombok.Getter;

@Getter
public class TopsAPI 
{
    private final TopsConfig config;
    private final Map<HologramsContainer, SortingProcessor<?, ?>> containers;
    private static TopsAPI instance;

    private TopsAPI(Plugin plugin){
        config = new TopsConfig(
             false,
            200
        );

        containers = new HashMap<>();
        startUpdater(plugin);
    }


    public static TopsAPI load(Plugin plugin){
        instance = new TopsAPI(plugin);
        return instance;
    }

    public <K,V> void appendContainer(HologramsContainer container, SortingProcessor<K, V> processor){
        containers.put(container, processor);
    }

    public void removeContainer(HologramsContainer container){
        containers.remove(container);
        container.clear();
    }

    private void startUpdater(Plugin plugin){
       new BukkitRunnable() {

           @Override
           public void run() {
               containers.keySet().forEach(container->{
                   if (container.getLocation().getWorld()==null) {
                       System.out.printf("TopsAPI: The world %s is null%n", container.getLocation().getWorld().getName());
                       return;
                   }
                   container.clear();
                   container.display(containers.get(container));
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
