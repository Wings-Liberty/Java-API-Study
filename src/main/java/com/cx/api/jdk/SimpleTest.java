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
        List<String> list = Lists.newArrayList("123", "456", "678");
        System.out.println(list.contains("345"));
        System.out.println(list.contains("678"));
    }

}
