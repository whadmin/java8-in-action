package com.wuhao.java8.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangwenjun on 2016/10/20.
 */
public class StreamFilter {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);

        List<Integer> result = list.stream().filter(i -> i % 2 == 0).filter(j-> j>2).collect(toList());

        System.out.println(result);

        result = list.stream().distinct().collect(toList());

        System.out.println(result);

        result = list.stream().skip(50).collect(toList());

        System.out.println(result);

        result = list.stream().limit(50).collect(toList());

        System.out.println(result);
    }
}
