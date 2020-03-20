package com.wuhao.java8.completableFuture;

import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction2 {

    @Test
    public void testEasyUse1() throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        /** 设置执行异步人无的线程为非守护线程 **/
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            // 设置为非守护线程
            t.setDaemon(false);
            return t;
        });
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    finished.set(true);
                });

        //如果未设置成非守护线程必须打开注释
//        while (!finished.get()) {
//            Thread.sleep(1);
//        }
    }
}
