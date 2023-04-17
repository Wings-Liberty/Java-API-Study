package com.cx.api.jdk;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SimpleTest {

    @Test
    public void test(){
        Map<String, Long> map = Maps.newHashMap();
        long sum = map.values().stream().mapToLong(t -> t).sum();
        System.out.println(sum);
    }

}
