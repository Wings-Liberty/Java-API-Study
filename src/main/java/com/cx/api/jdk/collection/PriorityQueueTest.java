package com.cx.api.jdk.collection;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    @Test
    public void testSortByDate(){
        // 堆的第一个元素是最大最小值，但遍历优先队列得到的数据不是有序的
        PriorityQueue<Pair<LocalDate, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.add(Pair.of(LocalDate.now(), 1));
        queue.add(Pair.of(LocalDate.now().plusDays(2L), 1));
        queue.add(Pair.of(LocalDate.now().plusDays(1L), 1));
        for (Pair<LocalDate, Integer> pair : queue) {
            System.out.println(pair);
        }
    }

}
