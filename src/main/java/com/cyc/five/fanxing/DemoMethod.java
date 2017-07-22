package com.cyc.five.fanxing;

/**
 * 泛型方法
 * 
 * @author cyc_e
 *
 */
public class DemoMethod {
	/**
	 * 在实例化类的时候，不用指定类型，比较灵活。此时方法返回类型前也要加上 <T>
	 * 
	 * @param t
	 * @return
	 */
	public <T> T fun(T t) { // 可以接收任意类型的数据
		return t; // 直接把参数返回
	}

}
