package com.cyc.serven.switchcase;

public class SwitchString {
	public static void main(String[] args) {
		String s = "test";
		switch (s) {
		case "test":
			System.out.println("test");
			break;
		case "test1":
			System.out.println("test1");
			break;
		default:
			System.out.println("break");
			break;
		}
	}
}
