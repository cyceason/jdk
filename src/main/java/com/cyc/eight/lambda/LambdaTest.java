package com.cyc.eight.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        test1();
    }

    /**
     * Lambda表达式简单例子
     */
    public static void test0() {
        Predicate<Integer> bigerThan6 = x -> x > 6;//声明一个Lambda表达式
        System.out.println(bigerThan6.test(7)); //结果是 true
        System.out.println(bigerThan6.negate().test(7));// 结果是false
    }

    /**
     * Stream对集合的操作, filter, count, collect, map
     */
    public static void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        long count = list.stream().filter(i -> {
            // 类似spark， 需要count里面的内容才会打印，否则不会打印
            System.out.println(i);
            return i > 2;
        }).count();
        System.out.println("count : " + count);

        List<Integer> list2 = list.stream().filter(i -> i > 1).collect(Collectors.toList());
        System.out.println("list2 : " + list2);


        // map的意思是，1对1转换
        List<Integer> list3 = list.stream().map(i -> i + 1).collect(Collectors.toList());
        System.out.println("list3 : " + list3);

        // flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        List<Integer> list4 = Stream.of(list, list3).flatMap(num -> num.stream()).collect(Collectors.toList());
        System.out.println("list4 : " + list4);
        // flatMap例子2
        List<Integer> list5 = list.stream().flatMap(i -> Stream.of(i, i - 10)).collect(Collectors.toList());
        System.out.println("list5 : " + list5);

        // reduce 操作
        Integer[] integerArray = new Integer[]{1, 2, 3, 4};
        int sumAll = Stream.of(integerArray).reduce(0, (sum, element) -> {
            System.out.println("sum : " + element);
            System.out.println("element : " + element);
            return sum + element;
        });// 给一个0是用来启动，的，若给-1，结果会是9
        System.out.println("sumAll : " + sumAll);

        // mapToInt用法　：　借助Stream对集合类进行统计
        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("最大值 ： " + intSummaryStatistics.getMax());
        System.out.println("最小值 ： " + intSummaryStatistics.getMin());

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


}
