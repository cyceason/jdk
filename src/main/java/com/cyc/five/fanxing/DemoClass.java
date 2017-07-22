package com.cyc.five.fanxing;

/**
 * 普通泛型
 * 
 * @author cyc_e
 *
 * @param <T>
 */
public class DemoClass<T> {
	private T var;

	public T getVar() {
		return var;
	}

	public void setVar(T var) {
		this.var = var;
	}
}
