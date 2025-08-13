package dev.m7wq.base;

import java.util.List;
import java.util.Map;

public interface Top<K, V> {

    List<Map.Entry<K,V>> getTop(Map<K,V> map, int limit);

}
