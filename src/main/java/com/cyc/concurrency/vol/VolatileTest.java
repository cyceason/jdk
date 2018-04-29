package com.cyc.concurrency.vol;

/**
 * 双重检查， 需要加上 volatile 才安全
 * Created by cyc_e on 2018/4/29.
 */
public class VolatileTest {

    private volatile static VolatileTest instance = null;

    private VolatileTest() {

    }

    public static VolatileTest getInstance() {
        if (instance == null) {
            synchronized (VolatileTest.class) {
                if (instance == null) {
                    instance = new VolatileTest();
                }
            }
        }
        return instance;
    }
}
