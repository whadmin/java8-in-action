package com.wuhao.java8.completableFuture;

import org.junit.Test;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: wuhao.w
 * @Date: 2020/3/19 17:37
 */
public class CompletableFutureInAction1 {

    private static final Random random = new Random(System.currentTimeMillis());

    @Test
    public void testEasyUse1() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            Double d = get();
            future.complete(d);
        }).start();
        System.out.println("=======noblock=====");
        Optional.ofNullable(future.get()).ifPresent(System.out::println);
    }

    public static void whenComplete() throws Exception {

    }


    public static double get() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = random.nextDouble();
        System.out.println(result);
        return result;
    }

    private static void execute() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
