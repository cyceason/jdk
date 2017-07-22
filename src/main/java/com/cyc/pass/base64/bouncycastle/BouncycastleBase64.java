package com.cyc.pass.base64.bouncycastle;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

/**
 * bouncycastle base64加解密
 * 
 * @author cyc_e
 *
 */
public class BouncycastleBase64 {
	public final static String ENCODING = "utf-8";

	/**
	 * 加密
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String encode(String date) throws Exception {
		byte[] b = Base64.encode(date.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	/**
	 * 解密
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String decode(String date) throws Exception {
		byte[] b = Base64.decode(date.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	/**
	 * url加密
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String urlEncode(String date) throws Exception {
		byte[] b = UrlBase64.encode(date.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	/**
	 * url解密
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String urlDecode(String date) throws Exception {
		byte[] b = UrlBase64.decode(date.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encode("加密"));
		System.out.println(decode("5Yqg5a+G"));
		System.out.println(urlEncode("加密"));
		System.out.println(urlDecode("5Yqg5a-G"));
	}
}
