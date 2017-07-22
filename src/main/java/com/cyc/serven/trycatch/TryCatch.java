package com.cyc.serven.trycatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryCatch {
	public static void main(String[] args) {
		try (BufferedReader in = new BufferedReader(
				new FileReader("D:\\workspace\\jdk\\src\\main\\java\\com\\cyc\\serven\\trycatch\\readme"));) {
			System.out.println(in.readLine());
			System.out.println(in.readLine());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
