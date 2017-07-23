package com.cyc.eight.lambda;

/**
 * 函数接口, 满足这样的条件：首先他的类型是interface，而且有且仅有一个抽象方法，然后有N(N>=0)个default方法和static方法
 * Created by cyc_e on 2017/7/23.
 */

@FunctionalInterface
public interface FunctionalInterfaceTest {
    /**
     * 抽象方法
     */
    public abstract void functionalInterfaceTest();


    default void defaultMethod1() {
        System.out.println("默认方法1");
    }

    default void defaultMethod2() {
        System.out.println("默认方法2");
    }
}
