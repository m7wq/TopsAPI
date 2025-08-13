package dev.m7wq.base;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/*
 * K, V -> The key and value types of the map that's getting compared
*/
@FunctionalInterface
public interface CompareMethod<K,V> {

    /**
     * @param map The map that is getting compared
     * @param limit The amount of sorted entries
     * @return List of sorted entries
    */
    List<Map.Entry<K,V>> compare(Map<K,V> map, int limit);
    

}
