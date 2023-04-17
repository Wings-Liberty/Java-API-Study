package com.cx.test.jdk.stream;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FlatMapTest {

    @Test
    public void test(){
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        System.out.println(list.stream().max(Integer::compareTo));
        System.out.println(list.stream().reduce(Integer::sum));
    }

}
