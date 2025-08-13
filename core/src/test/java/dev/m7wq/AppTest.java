package dev.m7wq;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import dev.m7wq.base.CompareMethod;
import dev.m7wq.base.Top;
import dev.m7wq.model.methods.ComparingByValue;
import dev.m7wq.model.tops.NameByNumberTop;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest{

    public static void main(String[] args) {


        // Your custom comparing method
        CompareMethod<String,Integer> compareMethod = (map,limit)->{
  
            return map.entrySet().stream()
                    .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        };

        // Built-in
        ComparingByValue comparingByValue = new ComparingByValue<>();


        // The benefit is just a better displaying for beginners
        // And also if you do advanced comparing process or something likely
        Top<String,Integer> top = (map,limit)->{

     
            List<Map.Entry<String,Integer>> simpleSort;

            simpleSort = new ComparingByValue<String, Integer>().compare(map, limit);

            return simpleSort;


        };

        top.getTop(null, 10);

        // Built In
        top = new NameByNumberTop();

        
    }

}
