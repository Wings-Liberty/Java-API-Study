package com.cx.api.jdk;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void test(){
        System.out.println(String.class.getName());
        System.out.println(String.class.getSimpleName());
        System.out.println(String.class.getTypeName());


        System.out.println(Lists.class.getName());
        System.out.println(Lists.class.getSimpleName());
        System.out.println(Lists.class.getTypeName());

    }

}
