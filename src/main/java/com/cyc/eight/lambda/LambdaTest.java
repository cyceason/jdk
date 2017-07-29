package com.cyc.eight.lambda;

import java.util.*;
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
    public static void main(String[] args) throws Exception {
        test3();
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
     * 使用双冒号运算， 格式是 ： 类名::方法名
     * 1. person -> person.getAge()  转换为 ：Person::getAge
     * 2. () -> new HashMap<>();     转换为 ： HashMap::new
     */
    public static void test2() {
        List<String> list = Arrays.asList("a", "b", "c");

        // Lambda表达式：
        list.forEach(n -> System.out.println(n));

        // 使用双冒号运算
        list.forEach(System.out::println);

        // Lambda表达式：
        List<String> list1 = list.stream().map(n -> n.toUpperCase()).collect(Collectors.toList());
        System.out.println(list1);

        // 使用双冒号运算
        List<String> list2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list2);

        // 使用双冒号运算
        List<String> list3 = list.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));//注意发生的变化
        System.out.println(list3);
    }

    /**
     * Optional是Java8里面用避免空指针的
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        Integer testInt[] = {1, 2, 3, 4};//空数组
        Optional<Integer> sumAll = Stream.of(testInt).reduce(Integer::sum);
        sumAll.ifPresent(x -> System.out.println(x));//sumAll不为空的时候，打印x的值；为空的时候，不做任何操作
        System.out.println(sumAll.orElse(0));// 给Optional一个默认值0，默认值是先new出来的
//        System.out.println(sumAll.orElseGet(() -> 0));// 给Optional一个默认值0， 缺省时再去new
        sumAll.orElseThrow(() -> new Exception("不能为空")); // 抛出异常

        // 以上所有例子中Optional都是方法返回给我们的，其实我们也可以自己new 一个Optional但是不是使用new关键字。而是使用 of(T)以及ofNullable(T) 等方法
        Optional<List<Integer>> optional = Optional.of(new ArrayList<>());//of()里面不能传入null, Of里面必须输入一个值，也就是说使用of创建的Optional对象一定不是empty的
        System.out.println(optional.get()); // []
        Optional<List<Integer>> optional1 = Optional.ofNullable(new ArrayList<>());// ofNullable是可以创建一个empty的Optional对象
        System.out.println(optional1.get()); // []
        Optional<List<Integer>> emptyOptional = Optional.empty();
        Optional<List<Integer>> emptyOptional2 = Optional.ofNullable(null);
        System.out.println(emptyOptional.equals(emptyOptional2)); //true

        // Optional除了这么苍白地使用，还可配合过滤转换一起使用
        System.out.println(sumAll.filter(x -> x > 7));//Optional[10]

        // flatMap里面传入的东西必须是Optional对象
        String testS[] = {"hello", " ", "world", " ", "!"};
        Optional<String> sumAll2 = Stream.of(testS).reduce(String::concat);
        System.out.println(sumAll2.flatMap(x -> Optional.ofNullable(null)));//Optional.empty
        System.out.println(sumAll2.flatMap(x -> Optional.of(x.toUpperCase())));//Optional[HELLO WORLD !]
    }


}
