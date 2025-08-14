package dev.m7wq.entity.process;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class SortingProcessorBuilder<K, V> {
    
    private Comparator<Map.Entry<K, V>> comparator;
    private Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper;
    private Map<K, V> map;
    private int limit;

    public SortingProcessorBuilder<K, V> comparator(Comparator<Map.Entry<K, V>> comparator) {
        this.comparator = comparator;
        return this;
    }

    public SortingProcessorBuilder<K, V> mapper(Function<Map.Entry<K, V>, Map.Entry<String, Integer>> mapper) {
        this.mapper = mapper;
        return this;
    }

    public SortingProcessorBuilder<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    public SortingProcessorBuilder<K, V> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public SortingProcessor<K, V> build() {
        return new SortingProcessor<>(comparator, mapper, map, limit);
    }
}

