package com.cyc.proxy.cglib;

public class Door {
	public boolean open() {
		System.out.println("open door");
		return true;
	}

	public void close() {
		System.out.println("open close");
	}
}
