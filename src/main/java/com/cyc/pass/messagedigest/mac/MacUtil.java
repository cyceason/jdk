package com.cyc.pass.messagedigest.mac;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class MacUtil {
	public final static String ENCODING = "utf-8";

	public static byte[] hmacMD5(byte[] data) throws Exception {
		// 初始化KeyGenerator
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
		// 产生密钥， 一个随机密钥
		SecretKey secretKey = keyGenerator.generateKey();
		// 获取二进制密钥
		byte[] key = secretKey.getEncoded();
		// 还原密钥
		secretKey = new SecretKeySpec(key, "HmacMD5");
		String algorithm = secretKey.getAlgorithm(); // 值为HmacMD5
		// 实例化Mac
		Mac mac = Mac.getInstance(algorithm);
		// 初始化 mac
		mac.init(secretKey);
		// 执行消息摘要, 缺少十六进制编码转换实现
		return mac.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Commonscodec.encode(MacUtil.hmacMD5("我我我".getBytes(ENCODING))));
	}
}
