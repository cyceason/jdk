package com.cyc.five.annotation.test;

import java.lang.reflect.Field;

import com.cyc.five.annotation.test.FruitColor.Color;

/**
 * 注解使用例子
 * 
 * @author Administrator
 *
 */
public class Apple {

	@FruitName("Apple")
	private String appleName;

	@FruitColor(fruitColor = Color.RED)
	private String appleColor;

	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}

	public String getAppleColor() {
		return appleColor;
	}

	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}

	public String getAppleName() {
		return appleName;
	}

	public void displayName() {
		System.out.println("水果的名字是：苹果");
	}

	public static void main(String[] args) throws Exception {

		String strFruitName = "水果名称：";
		String strFruitColor = " 水果颜色：";
		Class<?> clazz = Class.forName("com.cyc.five.annotation.test.Apple");
		Field[] fields = clazz.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			if (field.isAnnotationPresent(FruitName.class)) {
				FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
				strFruitName = strFruitName + fruitName.value();
				System.out.println(strFruitName);
			} else if (field.isAnnotationPresent(FruitColor.class)) {
				FruitColor fruitColor = (FruitColor) field.getAnnotation(FruitColor.class);
				strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
				System.out.println(strFruitColor);
			}
		}
	}
}
