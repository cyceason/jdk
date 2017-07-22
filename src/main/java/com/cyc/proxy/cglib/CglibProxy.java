package com.cyc.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy {
	private Enhancer enhancer = new Enhancer();

	/**
	 * 创建动态代理对象
	 * 
	 * @param clazz
	 * @return
	 */
	public Object getProxy(Class<?> clazz) {
		// 设置需要创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("begin");
				Object result = proxy.invokeSuper(obj, args); // 通过代理调用父类中的方法
				System.out.println("end");
				return result;
			}
		});
		// 通过字节码技术动态创建子类实例
		return enhancer.create();
	}

	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();
		Door proxy = (Door) cglibProxy.getProxy(Door.class);
		proxy.close();
	}

}
