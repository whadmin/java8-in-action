package com.wuhao.java8.function;

import com.wuhao.java8.model.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaUsage {


    /**
     * Predicate<T> for list
     *
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> simplePredicate(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a))
                result.add(a);
        }
        return result;
    }

    /**
     * XXXPredicate<XXX>
     *
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> simpleXXXPredicate(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight()))
                result.add(a);
        }
        return result;
    }

    /**
     * BiPredicate<T, U>
     *
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> simpleBiPredicate(List<Apple> source, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getColor(), a.getWeight()))
                result.add(a);
        }
        return result;
    }

    /**
     * Consumer<T>
     *
     * @param source
     * @param consumer
     */
    private static void simpleConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple a : source) {
            consumer.accept(a);
        }
    }

    /**
     * IntConsumer
     *
     * @param source
     * @param consumer
     */
    private static void simpleLongConsumer(List<Apple> source, LongConsumer consumer) {
        for (Apple a : source) {
            consumer.accept(a.getWeight());
        }
    }

    /**
     * BiConsumer<T, U>
     *
     * @param c
     * @param source
     * @param consumer
     */
    private static void simpleBiConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple a : source) {
            consumer.accept(a, c);
        }
    }

    /**
     * ObjXXXConsumer<T>
     *
     * @param source
     * @param consumer
     */
    private static void simpleObjIntConsumer(List<Apple> source, ObjIntConsumer<Apple> consumer) {
        for (Apple a : source) {
            consumer.accept(a, a.hashCode());
        }
    }

    /**
     * Function<T, R>  T-->R
     *
     * @param apple
     * @param fun
     * @return
     */
    private static Apple testFunction(Apple apple, Function<Apple, Apple> fun) {
        return fun.apply(apple);
    }

    /**
     * XXXFunction<R>   XXX-->R
     *
     * @param apple
     * @param fun
     * @return
     */
    private static Apple testIntFunction(Apple apple, LongFunction<Apple> fun) {
        return fun.apply(apple.getWeight());
    }

    /**
     * ToXXXFunction<T>   T --XXX
     *
     * @param apple
     * @param fun
     * @return
     */
    private static Long testToLongBiFunction(Apple apple, ToLongFunction<Apple> fun) {
        return fun.applyAsLong(apple);
    }

    /**
     * XXTOXXXFunction<R>   XX-->XXX
     *
     * @param apple
     * @param fun
     * @return
     */
    private static int testLongToIntFunction(Apple apple, LongToIntFunction fun) {
        return fun.applyAsInt(apple.getWeight());
    }

    /**
     * BiFunction<T, U, R>   (T,U) --R
     *
     * @param apple
     * @param fun
     * @return
     */
    private static Apple testBiFunction(Apple apple, BiFunction<String, Long, Apple> fun) {
        return fun.apply(apple.getColor(), apple.getWeight());
    }

    /**
     * ToXXXBiFunction<T, U>   (T,U) --XXX
     *
     * @param apple
     * @param fun
     * @return
     */
    private static Long testToLongBiFunction(Apple apple, ToLongBiFunction<String, Long> fun) {
        return fun.applyAsLong(apple.getColor(), apple.getWeight());
    }


    private static Apple testSupplier(Supplier<Apple> fun) {
        return fun.get();
    }


    private static Boolean testBooleanSupplier(BooleanSupplier fun) {
        return fun.getAsBoolean();
    }


    @org.junit.Test
    public void predicate() {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

        List<Apple> greenList = simplePredicate(list, (apple) -> apple.getColor().equals("green"));
        System.out.println(greenList);

        List<Apple> result = simpleXXXPredicate(list, w -> w > 100);
        System.out.println(result);

        List<Apple> result2 = simpleBiPredicate(list, (s, w) -> s.equals("green") && w > 100);
        System.out.println(result2);
    }


    @org.junit.Test
    public void consumer() {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        simpleConsumer(list, a -> System.out.println(a));
        simpleConsumer(list, a -> System.out.println(a));
        simpleBiConsumer("XXX", list, (a, s) -> System.out.println(s + ":Color=>" + a.getColor() + ":Weight=>" + a.getWeight()));
        simpleObjIntConsumer(list, (a, b) -> System.out.println(a + ":hashCode=>" + b));
    }

    @org.junit.Test
    public void funtion() {
        System.out.println(testFunction(new Apple("green", 120),p->{p.setColor("red");return p;}));
        System.out.println(testToLongBiFunction(new Apple("green", 120),p->p.getWeight()));
        System.out.println(testLongToIntFunction(new Apple("green", 120),p->(int)p));
        System.out.println(testBiFunction(new Apple("green", 120),(a,b)->new Apple(a, b)));
        System.out.println(testToLongBiFunction(new Apple("green", 120),(a,b)->b));
    }

    @org.junit.Test
    public void supplier() {
        System.out.println(testSupplier(()->new Apple("green", 120)));
        System.out.println(testBooleanSupplier(()->true));
    }


    public static void useTest(BiConsumer<Test, String> con, List<String> list) {

    }


    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    interface Test {
        public void say(String s);
    }
}
