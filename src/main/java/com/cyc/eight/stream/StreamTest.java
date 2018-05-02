package com.cyc.eight.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1.Stream是元素的集合，这点让Stream看起来用些类似Iterator；
 * 2.可以支持顺序和并行的对原Stream进行汇聚的操作；
 * Created by cyc_e on 2018/5/1.
 */
public class StreamTest {
    public static void main(String[] args) {
        testIterate();
    }

    /**
     * 测试reduce
     */
    public static void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().reduce((sum, item) -> sum + item).get());
    }

    /**
     * 测试collect
     */
    public static void testCollector() {
        List<Integer> list = Arrays.asList(1, 2, null, 3, 4, 5);
        List<Integer> collect = list.stream().filter((num) -> num != null).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    /**
     * 测试FlatMap, flatMap方法，可以将多个容器的元素全部映射到一个容器中，即为扁平的map
     */
    public static void testFlatMap() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        List<Integer> list1 = Stream.of(list, list).flatMap(num -> num.stream()).collect(Collectors.toList());
        // list1 : [1, 2, 3, 4, 5, 1, 2, 3, 4, 5]
        System.out.println("list1 : " + list1);
        // flatMap例子2
        List<Integer> list2 = list.stream().flatMap(i -> Stream.of(i, i - 10)).collect(Collectors.toList());
        // list2 : [1, -9, 2, -8, 3, -7, 4, -6, 5, -5]
        System.out.println("list2 : " + list2);
    }

    public static void test1() {
        List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println(nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                // peek : 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数
                        peek(System.out::println).skip(2).limit(4).sum());
    }

    /**
     * 统计信息
     */
    public static void test2() {
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9, 11);
        IntSummaryStatistics statistics = list1.stream().filter(integer -> integer > 2).mapToInt(i -> i * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());
    }

    /**
     * Stream.generate : 生成连续不断的流或者一个全部是随机数的流
     */
    public static void testGenerate() {
        Stream<String> generate = Stream.generate(UUID.randomUUID()::toString).limit(10);
        generate.forEach(System.out::println);
    }

    /**
     * iterate方法：也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
     */
    public static void testIterate() {
        Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);
    }


}
