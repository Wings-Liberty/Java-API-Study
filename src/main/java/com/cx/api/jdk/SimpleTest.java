package com.cx.api.jdk;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleTest {

    @Test
    public void test(){
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "123");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.getOrDefault(3, ""));
    }

}
