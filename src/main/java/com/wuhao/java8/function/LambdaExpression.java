package com.wuhao.java8.function;

import com.wuhao.java8.model.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpression {

    public static void main(String[] args) {
        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> flambda = s -> s.length();

        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");

        Runnable r = () -> {
        };

        Function<Apple, Boolean> f = (a) -> a.getColor().equals("green");
    }
}
