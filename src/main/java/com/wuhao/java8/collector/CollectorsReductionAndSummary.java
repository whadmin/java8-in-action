package com.wuhao.java8.collector;

import com.wuhao.java8.model.Dish;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.wuhao.java8.collector.CollectorsAction.menu;
import static java.util.stream.Collectors.toList;

public class CollectorsReductionAndSummary {


    /**
     * 传入Collectors.summarizingDouble（ToDoubleFunction）
     * 针对传入的函数返回的值做不同维度的统计
     */
    @Test
    public void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testSummarizingLong() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);


    }

    @Test
    public void testSummarizingInt() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }


    /***
     * Collectors.reducing(BinaryOperator<T> op) 用于计算流中的数据
     * 传入BinaryOperator函数实现计算逻辑
     */
    /**
     * 计算流中最大的Dish::getCalories
     */
    @Test
    public void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        menu.stream().collect(
                Collectors.reducing(
                        BinaryOperator.maxBy(
                                Comparator.comparingInt(Dish::getCalories)
                        )
                )
        ).ifPresent(System.out::println);
    }

    @Test
    public void testReducingBinaryOperatorAndIdentiy() {
        System.out.println("testReducingBinaryOperatorAndIdentiy");
        Integer result = menu.stream()
                .map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    @Test
    public void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }


    @Test
    public void testMapping() {
        System.out.println("testMapping");
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(","))))
                .ifPresent(System.out::println);
    }


    @Test
    public void testJoining() {
        System.out.println("testJoining");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining()))
                .ifPresent(System.out::println);
    }

    @Test
    public void testJoiningWithDelimiter() {
        System.out.println("testJoiningWithDelimiter");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
                .ifPresent(System.out::println);
    }

    @Test
    public void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]")))
                .ifPresent(System.out::println);
    }


    /**
     * Collectors.summingXXX 求sum
     */
    @Test
    public void testSummingDouble() {
        System.out.println("testSummingDouble");
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(menu.stream().mapToDouble(p -> new Integer(p.getCalories()).doubleValue()).sum())
                .ifPresent(System.out::println);

        menu.stream().mapToDouble(p -> new Integer(p.getCalories()).doubleValue()).reduce((a, b) -> a + b).ifPresent(System.out::println);

    }

    @Test
    public void testSummingLong() {
        System.out.println("testSummingLong");
        Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(menu.stream().map(Dish::getCalories).mapToLong(Integer::intValue).sum())
                .ifPresent(System.out::println);

        menu.stream().mapToLong(p -> p.getCalories()).reduce((a, b) -> a + b).ifPresent(System.out::println);
    }

    @Test
    public void testSummingInt() {
        System.out.println("testSummingInt");
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(menu.stream().mapToInt(Dish::getCalories).sum())
                .ifPresent(System.out::println);

        menu.stream().mapToInt(p -> p.getCalories()).reduce((a, b) -> a + b).ifPresent(System.out::println);
    }

    /**
     * 使用Collectors.maxBy求最大值
     */
    @Test
    public void testMaxBy() {
        System.out.println("testMaxBy");
        menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 使用Collectors.minBy求最小值
     */
    @Test
    public void testMinBy() {
        System.out.println("testMinBy");
        menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 使用Collectors.averagingXXX求平均值
     */
    @Test
    public void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 使用Collectors.counting求总数
     */
    @Test
    public void testCounting() {
        System.out.println("testCounting");
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        System.out.println(menu.stream().count());
        System.out.println(menu.stream().mapToInt(p -> 1).sum());
    }


    @Test
    public void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The Average Calories is->" + a)))
                .ifPresent(System.out::println);

        Integer collect1 = menu.stream().collect(Collectors.collectingAndThen(toList(), t -> t.size()));
    }


}
