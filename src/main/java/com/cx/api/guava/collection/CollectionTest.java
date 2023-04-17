package com.cx.api.guava.collection;

import cn.hutool.core.lang.Pair;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionTest {

    /**
     * 记录单词出现的次数
     */
    @Test
    public void testMultiSet(){
        // 词典
        String[] words = {"a", "b", "c", "d", "b", "c", "d", "c", "d", "d"};

        // 方法一：用 map 记录
        System.out.println("-----方法一：用 map 记录-----");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.merge(words[i], 1, Integer::sum);
        }
        map.forEach((key, value)->{
            System.out.println(key + " 出现了 " + value + "次");
        });

        // 方法二：用 MultiSet 记录
        System.out.println("-----方法二：用 MultiSet 记录-----");
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(Arrays.asList(words));
        multiset.entrySet().forEach(entry->{
            System.out.println(entry.getElement() + " 出现了 " + entry.getCount() + "次");
        });

    }

    @Test
    public void testMultiMap(){
        ArrayList<Pair<String, Integer>> pairList = new ArrayList<>();
        pairList.add(Pair.of("a", 1));
        pairList.add(Pair.of("a", 2));
        pairList.add(Pair.of("a", 3));
        pairList.add(Pair.of("b", 1));

        System.out.println("-----方法一：用 groupBy 做-----");
        Map<String, List<Pair<String, Integer>>> groupByResult = pairList.stream().collect(Collectors.groupingBy(Pair::getKey));
        groupByResult.forEach((key, valueList)->{
            System.out.println(key + " 的列表是 " + valueList);
        });

        System.out.println("-----方法一：用 Multimap 做-----");
        ListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        pairList.forEach(pair->{
            multimap.put(pair.getKey(), pair.getValue());
        });
        multimap.asMap().forEach((key, valueList)->{
            System.out.println(key + " 的列表是 " + valueList);
        });
    }

    @Test
    public void testBiMap(){
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("a", "A");
        biMap.put("b", "B");
        biMap.put("c", "C");
        biMap.forcePut("d", "D");
        System.out.println(biMap.get("a"));
        System.out.println(biMap.inverse().get("A"));
        System.out.println(biMap.get("d"));
    }

    @Test
    public void testTable(){
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("v1", "v2", 4);
        table.put("v1", "v3", 20);
        table.put("v2", "v3", 5);

        Map<String, Integer> rowMap = table.row("v1");
        rowMap.forEach((key, value)-> System.out.println(key + " : " + value));

        Map<String, Integer> columnMap = table.column("v3");
        columnMap.forEach((key, value)-> System.out.println(key + " : " + value));
    }


}
