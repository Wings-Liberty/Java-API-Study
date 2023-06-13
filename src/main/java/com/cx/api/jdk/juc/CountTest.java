package com.cx.api.jdk.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountTest {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        countDownLatch.await(30L, TimeUnit.SECONDS);
        System.out.println("-----");
    }

}
