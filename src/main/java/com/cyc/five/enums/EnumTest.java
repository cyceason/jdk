package com.cyc.five.enums;

public class EnumTest {
	public static void main(String[] args) {
		for (EnumWeek e : EnumWeek.values()) {
			System.out.println(e.toString());
		}

		System.out.println("----------------我是分隔线------------------");

		EnumWeek enumWeek = EnumWeek.TUE;
		switch (enumWeek) {
		case MON:
			System.out.println("今天是星期一");
			break;
		case TUE:
			System.out.println("今天是星期二");
			break;
		default:
			System.out.println(enumWeek);
			break;
		}
	}

}
