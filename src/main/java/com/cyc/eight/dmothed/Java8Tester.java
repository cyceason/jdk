package com.cyc.eight.dmothed;


/**
 * Java 8 新增了接口的默认方法
 * 默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法.只需在方法名前面加个default关键字即可实现默认方法
 * Created by cyc_e on 2018/5/1.
 */
public class Java8Tester {
    public static void main(String args[]) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    /**
     * Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法， 只能通过类调用，不能通过实例调用
     */
    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {
    @Override
    public void print() {
        /*
            1. 一个类实现了多个接口，且这些接口有相同的默认方法，使用 super 来调用指定接口的默认方法
            2. 只有直接父类才可以通过XX.super.xxMethod()的方式调用父类默认方法
         */
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}