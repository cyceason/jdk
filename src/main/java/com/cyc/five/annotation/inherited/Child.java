package com.cyc.five.annotation.inherited;

import java.lang.reflect.Method;

/**
 * Child继承了Parent中的InheritedTest注解
 * Created by cyc_e on 2017/6/11.
 */
public class Child extends Parent {

    /**
     * 由于重载了方法，不继承父类方法中的InheritedTest。 如果不重载，则继承父类的InheritedTest
     */
    @Override
    public void method() {
    }


    public static void main(String[] args) throws Exception {
        Class<Child> clazz = Child.class;
        //对类进行测试
        System.out.println("对类进行测试");
        if (clazz.isAnnotationPresent(InheritedTest.class)) {
            System.out.println(clazz.getAnnotation(InheritedTest.class).value());
        }
        System.out.println("==============================");
        //对方法 进行测试
        System.out.println("对方法进行测试");
        Method method = clazz.getMethod("method", null);
        if (method.isAnnotationPresent(InheritedTest.class)) {
            System.out.println(method.getAnnotation(InheritedTest.class).value());
        }
    }
}