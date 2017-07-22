package com.cyc.pass.base64.commonscodec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Commonscodec {
	public final static String ENCODING = "utf-8";

	/**
	 * base64一般编码
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] data) throws Exception {
		byte[] b = Base64.encodeBase64(data);
		return new String(b, ENCODING);
	}

	/**
	 * base64安全编码，遵循RFC 2045实现。RFC 2045规范 ： 每行为76个字符，每行末添加一个回车符
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String encodeSafe(String date) throws Exception {
		byte[] b = Base64.encodeBase64(date.getBytes(ENCODING), true);
		return new String(b, ENCODING);
	}

	/**
	 * url加密
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String urlEncodeSafe(String date) throws Exception {
		return Base64.encodeBase64URLSafeString(date.getBytes(ENCODING));
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String data) throws UnsupportedEncodingException {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encode("加密".getBytes(ENCODING)));
		System.out.println(encodeSafe("加密"));
		System.out.println(urlEncodeSafe("加密"));
	}
}
