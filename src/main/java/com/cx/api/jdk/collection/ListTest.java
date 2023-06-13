package com.cx.api.jdk.collection;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    public void testSort(){
        List<Pair<LocalDate, Integer>> unSortList = Lists.newArrayList();
        unSortList.add(Pair.of(LocalDate.now(), 1));
        unSortList.add(Pair.of(LocalDate.now().plusDays(2L), 1));
        unSortList.add(Pair.of(LocalDate.now().plusDays(1L), 1));
        // 自然排序（从小到大）
        List<Pair<LocalDate, Integer>> natureSortList = unSortList.stream().sorted().collect(Collectors.toList());
        natureSortList.forEach(System.out::println);
        // 逆序排序
        List<Pair<LocalDate, Integer>> reverseSortList = unSortList.stream().sorted(Comparator.<Pair<LocalDate, Integer>, LocalDate>comparing(Pair::getKey).reversed()).collect(Collectors.toList());
        reverseSortList.forEach(System.out::println);


    }

}
