package com.cyc.eight.finterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 1. 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 * 2. 函数式接口可以被隐式转换为lambda表达式
 *
 * @FunctionalInterface注解， 如果接口符合1中的定义，则可以无需该注解，注解主要用于编译级错误检查
 * JDK 1.8 之前已有的函数式接口:
 * <p>
 * java.lang.Runnable
 * java.util.concurrent.Callable
 * java.security.PrivilegedAction
 * java.util.Comparator
 * java.io.FileFilter
 * java.nio.file.PathMatcher
 * java.lang.reflect.InvocationHandler
 * java.beans.PropertyChangeListener
 * java.awt.event.ActionListener
 * javax.swing.event.ChangeListener
 * <p>
 * JDK 1.8 新增加的函数接口 ： java.util.function包下面
 * Created by cyc_e on 2018/5/1.
 */
public class Java8Tester {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("输出所有数据:");

        // 传递参数 n
        eval(list, n -> n > 4);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
    }
}