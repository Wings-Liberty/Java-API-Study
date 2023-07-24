package com.cx.api.jdk.stream;

import com.cx.helper.bean.Dog;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {


    @Test
    public void testToMap() {
        Dog dog1 = new Dog("111");
        Dog dog2 = new Dog("222");
        Dog dog3 = new Dog("111");
        ArrayList<Dog> list = Lists.newArrayList(dog1, dog2, dog3);
        Map<String, Dog> map = list.stream().collect(Collectors.toMap(Dog::getName, Function.identity(), (key1, key2) -> key2));
        map.forEach((key, value) -> System.out.println("key:" + key + "; value=" + value));
    }


    @Test
    public void testFind() {
        Dog dog1 = new Dog("111");
        Dog dog2 = new Dog("222");
        Dog dog3 = new Dog("333");
        ArrayList<Dog> list = Lists.newArrayList(dog1, dog2, dog3);
        Dog findResult = list.stream().filter(dog -> "333".equals(dog.getName())).findFirst().orElseThrow(() -> new RuntimeException("找不到，滚"));
    }


    /**
     * 逻辑分页
     */
    @Test
    public void logicPage(){
        List<String> list = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        // 每页 5 个，查第 2 页
        List<String> page2 = list.stream().skip(5 * 1).limit(5).collect(Collectors.toList());
        System.out.println("===== 每页 5 个，查第 2 页 =====");
        page2.forEach(System.out::println);
        // 每页 5 个，查第 3 页
        List<String> page3 = list.stream().skip(5 * 2).limit(5).collect(Collectors.toList());
        System.out.println("===== 每页 5 个，查第 3 页 =====");
        page3.forEach(System.out::println);
        // 每页 5 个，查第 1 页
        List<String> page1 = list.stream().skip(5 * 0).limit(5).collect(Collectors.toList());
        System.out.println("===== 每页 5 个，查第 1 页 =====");
        page1.forEach(System.out::println);
    }
}
