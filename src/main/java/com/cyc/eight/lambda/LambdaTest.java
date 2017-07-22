package com.cyc.eight.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * lambda表达式, 语法 ： 1. (params) -> expression    2. (params) -> statement    3. (params) -> { statements }
 * 特性
 * 1. 可选类型声明 - 无需声明参数的类型。编译器可以从该参数的值推断
 * 2. 可选圆括号参数 - 无需在括号中声明参数。对于多个参数，括号是必需的。
 * 3. 可选大括号 - 表达式主体没有必要使用大括号，如果主体中含有一个单独的语句。
 * 4. 可选return关键字 - 编译器会自动返回值，如果主体有一个表达式返回的值。花括号是必需的，以表明表达式返回一个值。
 * Created by cyc_e on 2017/7/11.
 */
public class LambdaTest {
    public static void main(String[] args) {
        test3();
    }

    public static void test0() {
        Predicate<Integer> bigerThan6 = x -> x > 6;//声明一个Lambda表达式
        System.out.println(bigerThan6.test(7)); //结果是 true
        System.out.println(bigerThan6.negate().test(7));// 结果是false
    }

    /**
     * 代替匿名函数
     */
    public static void test1() {
        // 匿名类写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //　lambda表达式写法
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    public static void test2() {

        // Java 8之前：
        List<String> list = Arrays.asList("1", "2", "3");
        for (String feature : list) {
            System.out.println(feature);
        }

        // Java 8之后：
        list.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        list.forEach(System.out::println);

    }

    public static void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> personSublist = list.stream().filter(i -> i > 2).collect(Collectors.toList());
        System.out.println(personSublist);

    }


    /**
     * 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成
     */
    public static void simpleTest() {
        /*
         * 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成
         */
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        System.out.println("--------------------------------------");
        /*
        在上面这个代码中的参数e的类型是由编译器推理得出的，你也可以显式指定该参数的类型，例如：
         */
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));
        System.out.println("--------------------------------------");
        /*
        如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体，例如：
         */
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });
        System.out.println("--------------------------------------");

        /*
        Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的）
         */
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.println(e + separator));

        System.out.println("--------------------------------------");
        /*
        Lambda表达式有返回值，返回值的类型也由编译器推理得出。如果Lambda表达式中的语句块只有一行，则可以不用使用return语句
         */
        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

    }


}
