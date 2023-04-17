package com.cx.test.jdk;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SetTest {

    @Test
    public void printTest(){
        Set<String> set = Sets.newHashSet("aaa","bbb", "ccc", "ddd");
        System.out.println(set);
    }

}
