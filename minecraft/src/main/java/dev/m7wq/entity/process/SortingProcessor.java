package dev.m7wq.entity.process;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import dev.m7wq.configs.HologramConfig;
import dev.m7wq.entity.holograms.Hologram;
import dev.m7wq.entity.holograms.HologramLine;
import dev.m7wq.hooks.LuckPermsHook;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SortingProcessor<K,V> {

    public static <K,V> SortingProcessorBuilder<K,V> builder(){
        return new SortingProcessorBuilder<K,V>();
    }

    private Comparator<Map.Entry<K, V>> comparator;
    private Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper;
    private Map<K, V> map;
    private int limit;

    public void process(List<Hologram> holograms, HologramConfig config){
        
        holograms.add(new Hologram(config.getHeader(), config));

        List<Map.Entry<K, V>> entries = map.entrySet()
                .stream()
                .sorted(comparator.reversed())
                .limit(limit)
                .toList();

    
        int rank = 1;

        for (Entry<K,V> entry : entries) {

            Map.Entry<String, Integer> finalEntry;


            if (entry == null) {

                finalEntry = Map.entry("None", 0);

            } else {

                Map.Entry<String, Integer> mapped = mapper.apply(entry);

                if (mapped == null) {

                    finalEntry = Map.entry("None", 0);

                } else {

                    String key = mapped.getKey()!=null ? mapped.getKey() : "None";
                    Integer value = mapped.getValue()!=null ? mapped.getValue() : 0;

                    finalEntry = Map.entry(key, value);
                }
            }

            HologramLine line = HologramLine.builder()
                                    .rank(rank)
                                    .amount(finalEntry.getValue())
                                    .name(LuckPermsHook.format(finalEntry.getKey(), config)).build();

            holograms.add(new Hologram(line, config));
            rank++;
            
        }
    }

}
