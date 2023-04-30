package com.cx.api.jdk.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class CompletableFutureTest {

    /**
     * 创建一个 CompletableFuture 令其执行任务
     *
     * 如果任务正常执行，就返回一个值（也可以不反悔）
     *
     * 如果任务执行时抛异常，就调用指定的异常方法，并返回一个值（也可以不反悔）
     *
     */
    @Test
    public void setExceptionFunc() throws InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行任务1");
            return 1/0;
        });
        cf.exceptionally(e-> {
            System.out.println("停止任务1，返回默认值 0，原因: " + e.getLocalizedMessage());
            return 0;
        });
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 串行执行 2 个任务后输出任务结果
     */
    @Test
    public void serial() throws InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成任务1");
            return 1;
        });
        CompletableFuture<Integer> cf2 = cf1.thenApplyAsync(num -> {
            System.out.println("完成任务2");
            return num + 1;
        });
        cf2.thenAccept(num->{
            System.out.println("完成了 " + num + " 个任务");
        });

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 并行执行两个任务，只要有一个任务完成就能结束
     */
    @Test
    public void parallelAndAnyOf() throws InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 1 号任务");
            return 1;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 2 号任务");
            return 2;
        });

        // 合并两个任务
        CompletableFuture<Object> merge = CompletableFuture.anyOf(cf1, cf2);

        // cf1 和 cf2 只要有一个任务完成了就执行输出。但 cf1 先完成后会执行下面的输出，但 cf2 仍会执行，不过不再执行 merge 的输出
        merge.thenAccept(code-> System.out.println("完成了一个任务，任务编号: " + code));

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 两个任务并行执行，合并后再分出两个并行任务
     */
    @Test
    public void parallelAndDispersed() throws InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 1 号任务");
            return 1;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 2 号任务");
            return 2;
        });

        // 合并两个任务
        CompletableFuture<Object> merge = CompletableFuture.anyOf(cf1, cf2);

        // cf1 和 cf2 只要有一个任务完成了就执行输出。但 cf1 先完成后会执行下面的输出，但 cf2 仍会执行，不过不再执行 merge 的输出
        merge.thenAccept(code-> System.out.println("优先完成了 " + code + " 号任务"));

        CompletableFuture<Object> cf3 = merge.thenApplyAsync(code -> {
            System.out.println("完成 " + code + " 号任务后完成 3 号任务");
            return 3;
        });
        CompletableFuture<Object> cf4 = merge.thenApplyAsync(code -> {
            System.out.println("完成 " + code + " 号任务后完成 4 号任务");
            return 3;
        });

        CompletableFuture<Object> mergeAgain = CompletableFuture.anyOf(cf3, cf4);
        mergeAgain.thenAccept(code->{
            System.out.println("优先完成了 " + code + " 号任务");
        });

        TimeUnit.SECONDS.sleep(2);
    }


    /**
     * 并行完成两个任务，都完成后再输出
     */
    @Test
    public void parallelAndAllOf() throws InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 1 号任务");
            return 1;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("完成 2 号任务");
            return 2;
        });

        // 合并两个任务
        CompletableFuture<Void> merge = CompletableFuture.allOf(cf1, cf2);

        // cf1 和 cf2 只要有一个任务完成了就执行输出。但 cf1 先完成后会执行下面的输出，但 cf2 仍会执行，不过不再执行 merge 的输出
        merge.thenAccept(code-> System.out.println("完成了所有任务" + code));

        TimeUnit.SECONDS.sleep(2);
    }
}
