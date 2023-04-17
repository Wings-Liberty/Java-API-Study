package com.cx.test.jdk.stream;

import com.cx.bean.Dog;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMapTest {

    @Test
    public void test() {
        Dog dog1 = new Dog("111");
        Dog dog2 = new Dog("222");
        Dog dog3 = new Dog("111");
        ArrayList<Dog> list = Lists.newArrayList(dog1, dog2, dog3);
        Map<String, Dog> map = list.stream().collect(Collectors.toMap(Dog::getName, Function.identity(), (key1, key2)->key2));
        map.forEach((key, value) -> System.out.println("key:" + key + "; value=" + value));
    }


}
