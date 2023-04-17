package com.cx.test.jdk.collection;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class CollectionTest {

    /**
     * 有序的set，遍历 set 时的顺序不是乱序的
     */
    @Test
    public void testVisitLinkedHashSet(){
        Set<String> set = Sets.newLinkedHashSet();
        set.add("qwe");
        set.add("asd");
        set.add("zxc");
        set.add("fix");
        set.add("idy");
        for (int cnt = 1; cnt <= 10; cnt++) {
            System.out.println("========== 第 " + cnt + " 次遍历 ==========");
            for (String str : set) {
                System.out.println(str);
            }
        }
    }

    /**
     * 无序 set，遍历 set 时每次遍历的结果都不一样
     */
    @Test
    public void testVisitHashSet(){
        Set<String> set = Sets.newHashSet();
        set.add("qwe");
        set.add("asd");
        set.add("zxc");
        set.add("fix");
        set.add("idy");
        for (int cnt = 1; cnt <= 10; cnt++) {
            System.out.println("========== 第 " + cnt + " 次遍历 ==========");
            for (String str : set) {
                System.out.println(str);
            }
        }
    }


}
