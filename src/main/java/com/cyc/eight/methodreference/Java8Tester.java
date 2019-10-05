package com.cyc.eight.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Java 8 方法引用
 * 方法引用通过方法的名字来指向一个方法。
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * 方法引用使用一对冒号 :: 。
 * Created by cyc_e on 2018/5/1.
 */
public class Java8Tester {

    public static void main(String args[]) {
        test2();
    }


    public static void test2() {
        // 构造器引用：它的语法是Class::new ：
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        // 静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);

        // 引用某个类型的任意对象的实例方法：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);

        // 引用某个对象的实例方法：它的语法是instance::method实例如下：
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    public static void test1() {
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}

class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
