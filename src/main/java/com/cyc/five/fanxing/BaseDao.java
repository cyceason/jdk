package com.cyc.five.fanxing;

import java.lang.reflect.ParameterizedType;

public class BaseDao<T> {
	private Class<?> T;

	/**
	 * this 指的是子类。 谁继承BaseDao，this 就指谁。 super也是指子类
	 */
	public BaseDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();// 获取父类的泛型
		this.T = (Class<?>) pt.getActualTypeArguments()[0];// 获取第一个泛型
		System.out.println(T);
	}

}
