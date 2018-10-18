package com.wuhao.java8.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class StreamCreate {
    @Test
    public void createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "alex", "wangwenjun", "world", "stream");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void createStreamFromValues() {
        Stream.of("hello", "alex", "wangwenjun", "world", "stream").forEach(System.out::println);
    }

    @Test
    public void createStreamFromArrays() {
        String[] strings = {"hello", "alex", "wangwenjun", "world", "stream"};
        Arrays.stream(strings).forEach(System.out::println);
    }

    @Test
    public void createStreamFromFile() {
        Path path = Paths.get("C:\\Users\\wangwenjun\\IdeaProjects\\java8\\java8-sharing\\src\\main\\java\\com\\wangwenjun\\java8\\StreamCreate.java");
        try (Stream<String> streamFromFile = Files.lines(path)) {
            streamFromFile.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createStreamFromIterator() {
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    }

    @Test
    public void createStreamFromGenerate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void createObjStreamFromGenerate() {
        Stream.generate(new ObjSupplier()).limit(10).forEach(System.out::println);
    }

    static class ObjSupplier implements Supplier<Obj> {

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
