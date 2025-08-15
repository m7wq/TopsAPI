package dev.m7wq.entity.process.impl;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class MemorySortingProcessorBuilder<K, V> {
    
    private Comparator<Map.Entry<K, V>> comparator;
    private Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper;
    private Map<K, V> map;
    private int limit;

    public MemorySortingProcessorBuilder<K, V> comparator(Comparator<Map.Entry<K, V>> comparator) {
        this.comparator = comparator;
        return this;
    }

    public MemorySortingProcessorBuilder<K, V> mapper(Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper) {
        this.mapper = mapper;
        return this;
    }

    public MemorySortingProcessorBuilder<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    public MemorySortingProcessorBuilder<K, V> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public MemorySortingProcessor<K, V> build() {
        return new MemorySortingProcessor<>(comparator, mapper, map, limit);
    }
}

