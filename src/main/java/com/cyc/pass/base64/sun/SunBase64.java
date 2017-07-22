package com.cyc.pass.base64.sun;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64， 不推荐使用sun包下面的类
 * 
 * @author cyc_e
 *
 */
public class SunBase64 {

	/**
	 * 编码, 编码后的字符串有个回车换成符
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String encodeApi(byte[] bytes) {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		BASE64Encoder base = new BASE64Encoder();
		return base.encode(bytes);
	}

	/**
	 * 解密, 原生aip
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeApi(String str) throws IOException {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		BASE64Decoder base = new BASE64Decoder();
		return base.decodeBuffer(str);
	}

	public static void main(String[] args) {
		System.out.println(SunBase64.encodeApi("aaa".getBytes()));
	}
}
