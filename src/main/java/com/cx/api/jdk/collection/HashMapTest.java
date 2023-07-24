package com.cx.api.jdk.collection;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class HashMapTest {

    @Test
    public void testValuesToArray(){
        Map<String, String> map = Maps.newHashMap();
        map.put("q", "q");
        map.put("w", "w");
        String[] array = map.values().toArray(new String[]{});
        Arrays.stream(array).forEach(System.out::println);
    }

}
