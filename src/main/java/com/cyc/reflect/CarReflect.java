package com.cyc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * java类反射技术
 * 
 * @author cyc_zero
 *
 */
public class CarReflect {

	public static Car initByDefaultConst() throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?> clazz = loader.loadClass("com.cyc.reflect.Car");

		Constructor<?> cons = clazz.getDeclaredConstructor();
		Car car = (Car) cons.newInstance();

		Method setBrand = clazz.getMethod("setBrand", String.class);
		setBrand.invoke(car, "红旗CA72");
		Method setColor = clazz.getMethod("setColor", String.class);
		setColor.invoke(car, "黑色");
		Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
		setMaxSpeed.invoke(car, 200);
		return car;
	}

	public static Car initByParamConst() throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?> clazz = loader.loadClass("com.cyc.reflect.Car");
		Constructor<?> cons = clazz.getDeclaredConstructor(String.class, String.class, int.class);
		Car car = (Car) cons.newInstance("吉利TOPMIX", "绿色", 120);
		return car;
	}

	public static void main(String[] args) throws Throwable {
		Car car1 = initByDefaultConst();
		Car car2 = initByParamConst();
		car1.introduce();
		car2.introduce();
	}
}
