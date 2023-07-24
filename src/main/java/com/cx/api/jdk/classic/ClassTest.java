package com.cx.api.jdk.classic;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void test(){
        // java.lang.String
        System.out.println(String.class.getName());
        // String
        System.out.println(String.class.getSimpleName());
        // java.lang.String
        System.out.println(String.class.getTypeName());
        // com.google.common.collect.Lists
        System.out.println(Lists.class.getName());
        // Lists
        System.out.println(Lists.class.getSimpleName());
        // com.google.common.collect.Lists
        System.out.println(Lists.class.getTypeName());

    }

}
