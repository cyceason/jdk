package com.cyc.proxy.inter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 * 
 * @author Administrator
 *
 */
public class ProxyTest {
	public static void main(String[] args) {
		final IHello hello = new Hello();
		// 第一个参数 ： 类加载器
		// 第二个参数： 接口
		// 第三个参数 ： InvocationHandler
		IHello newProxyInstance = (IHello) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), hello.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("begin");
						Object object = method.invoke(hello, args);
						System.out.println("end");
						return object;
					}
				});
		System.out.println(newProxyInstance.hello());
	}
}
