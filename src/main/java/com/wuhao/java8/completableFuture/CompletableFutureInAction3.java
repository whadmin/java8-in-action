package com.wuhao.java8.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @Author: wuhao.w
 * @Date: 2020/3/19 18:40
 */
public class CompletableFutureInAction3 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
//            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
//            }
            System.out.println("run end ...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }

        });
//        future.exceptionally(new Function<Throwable, Void>() {
////            @Override
////            public Void apply(Throwable t) {
////                System.out.println("执行失败！"+t.getMessage());
////                return null;
////            }
////        });

        TimeUnit.SECONDS.sleep(500);
    }
}
