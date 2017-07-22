package com.cyc.proxy.inter;

public class Hello implements IHello {

	public String sayHello(String name) {
		System.out.println(name);
		return "hello : " + name;
	}

	public String hello() {
		return "hello";
	}

}
