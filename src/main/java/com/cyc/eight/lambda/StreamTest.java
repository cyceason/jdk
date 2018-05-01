package com.cyc.eight.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1.Stream是元素的集合，这点让Stream看起来用些类似Iterator；
 * 2.可以支持顺序和并行的对原Stream进行汇聚的操作；
 * Created by cyc_e on 2018/5/1.
 */
public class StreamTest {
    public static void main(String[] args) {
        testReduce();
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
     * 测试FlatMap
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
}
