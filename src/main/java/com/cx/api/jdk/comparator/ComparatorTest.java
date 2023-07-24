package com.cx.api.jdk.comparator;

import com.cx.helper.bean.Dog;
import com.cx.helper.bean.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Object 提供了只有一个参数的 compareTo 方法
 *
 *
 */
public class ComparatorTest {

    @Test
    public void testComparable(){
        Integer t1 = 1;
        Integer t2 = 2;
        System.out.println(t1.compareTo(t2));
    }

    @Test
    public void testComparator(){
        User user1 = new User(1L, "1111", "123", new Dog());
        User user2 = new User(2L, "111", "123", new Dog());
        User user3 = new User(3L, "11", "123", new Dog());
        User user4 = new User(4L, "1", "123", new Dog());
        User[] users = new User[]{user4, user3, user2, user1};

        // 根据有 compare 方法的类排序
        Arrays.sort(users, Comparator.comparing(User::getId));
        Arrays.stream(users).forEach(System.out::println);
        // 自定义排序规则
        Arrays.sort(users, Comparator.comparing(User::getName, Comparator.comparingInt(String::length)).thenComparing(User::getId));
        Arrays.stream(users).forEach(System.out::println);
        // 自然排序和逆序
        Arrays.sort(users, Comparator.naturalOrder());
        Arrays.stream(users).forEach(System.out::println);
        Arrays.sort(users, Comparator.reverseOrder());
        Arrays.stream(users).forEach(System.out::println);
    }

    @Test
    public void testSortByStrLen(){
        String[] strs = new String[] {"aa", "bbb", "cccccc", "dddd"};
        // Arrays.sort 会打乱原有数组内部排序
//        Arrays.sort(strs, Comparator.comparingInt(String::length).reversed());
        String maxLenStr= Arrays.stream(strs).sorted(Comparator.comparingInt(String::length).reversed()).findFirst().orElse("");
        System.out.println(Arrays.toString(strs));
        System.out.println(maxLenStr);
    }

    @Test
    public void test2(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7);
        Integer max = list.stream().max(Integer::compareTo).get();
        System.out.println(max);
    }

}
