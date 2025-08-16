package dev.m7wq.entity.holograms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.m7wq.TopsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import dev.m7wq.configs.HologramConfig;
import dev.m7wq.entity.process.SortingProcessor;
import dev.m7wq.entity.process.impl.MemorySortingProcessor;
import lombok.AllArgsConstructor;

import lombok.Getter;


@AllArgsConstructor
@Getter

public class HologramsContainer {

    private HologramConfig config;
    private Location location;
    private final List<Hologram> holograms = new ArrayList<>();





    public void clear(){

        holograms.forEach(Hologram::delete);
        holograms.clear();
    }

    public void display(SortingProcessor processor) {

        CompletableFuture.supplyAsync(()->{

            final List<Hologram> holograms = new ArrayList<>();

            processor.process(holograms, config);
            return holograms;
        }).thenAccept(holograms->{


            Bukkit.getScheduler().runTask(TopsAPI.getInstance().getPlugin(), ()->{
                double spacing = config.getLineSpacing();



                for(int i = 0; i < holograms.size(); i++){
                    holograms.get(i).create(location.clone().add(0,-i*spacing,0));
                }
            });


        });
        




        

       
    }


}
