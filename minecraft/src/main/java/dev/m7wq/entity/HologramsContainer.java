package dev.m7wq.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.Location;

import dev.m7wq.TopsAPI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class HologramsContainer<K,V> {

    private Location location;
    private Comparator<Map.Entry<K, V>> comparator;
    private Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper;
    private Map<K, V> map;
    private int limit;

    private final List<Hologram> holograms = new ArrayList<>();


    private void append(Hologram hologram){
        holograms.add(hologram);
    }

    public void clear(){
        holograms.forEach(h->h.delete());
    }

    public void display() {

        List<Map.Entry<K, V>> entries = map.entrySet()
                .stream()
                .sorted(comparator.reversed())
                .limit(limit)
                .collect(Collectors.toList());

    
        int rank = 1;

        for (Entry<K,V> entry : entries) {

            Map.Entry<String, Integer> finalEntry = mapper.apply(entry);

            HologramLine line = HologramLine.builder()
                                    .rank(rank)
                                    .amount(finalEntry.getValue())
                                    .name(finalEntry.getKey()).build();

            append(new Hologram(line));
            rank++;
            
        }

        double spacing = TopsAPI.getInstance().getConfig().getLineSpacing();

        
        for(int i = 0; i < holograms.size(); i++){
            holograms.get(i).create(location.clone().add(0,-i*spacing,0));
        }

            

       
    }


}
