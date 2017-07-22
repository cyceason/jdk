package com.cyc.pass.messagedigest.crc;

import java.util.zip.CRC32;

public class CRCTest {
	public static void main(String[] args) {
		String str = "测试crc-32";
		CRC32 crc32 = new CRC32();
		// 使用指定的字节数组更新CRC32
		crc32.update(str.getBytes());
		// 返回CRC32值
		String hex = Long.toHexString(crc32.getValue());
		System.out.println("原文 ： " + str);
		System.out.println("crc32 ： " + hex);
	}
}
