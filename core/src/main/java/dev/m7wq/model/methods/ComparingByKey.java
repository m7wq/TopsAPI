package dev.m7wq.model.methods;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import dev.m7wq.base.CompareMethod;

public class ComparingByKey<K extends Comparable<K>,V> implements CompareMethod<K,V>{



    @Override
    public List<Entry<K, V>> compare(Map<K, V> map, int limit) {
               return (List<Entry<K, V>>) map.entrySet()
                .stream()
                .sorted(Map.Entry.<K,V>comparingByKey().reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Comparator geComparator(){
        return Map.Entry.<K,V>comparingByKey().reversed();
    }

}
