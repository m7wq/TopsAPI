package dev.m7wq.model.methods;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import dev.m7wq.base.CompareMethod;

public class ComparingByValue<K,V extends Comparable<V>> implements CompareMethod<K,V>{

    @Override
    public List<Map.Entry<K, V>> compare(Map<K, V> map, int limit) {
        return map.entrySet()
        .stream()
        .sorted(Map.Entry.<K,V>comparingByValue().reversed())
        .limit(limit)
        .collect(Collectors.toList());
    }

    public Comparator geComparator(){
        return Map.Entry.<K,V>comparingByValue().reversed();
    }



}
