package dev.m7wq.model.tops;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dev.m7wq.base.CompareMethod;
import dev.m7wq.base.Top;
import dev.m7wq.model.methods.ComparingByValue;

public class NameByNumberTop implements Top<String, Integer> {

    @Override
    public List<Map.Entry<String, Integer>> getTop(Map<String, Integer> map, int limit) {

        ComparingByValue comparingByValue = new ComparingByValue<String, Integer>();

        return comparingByValue.compare(map, limit);
    }

}
