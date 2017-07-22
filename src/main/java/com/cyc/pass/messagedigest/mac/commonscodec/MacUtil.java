package com.cyc.pass.messagedigest.mac.commonscodec;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class MacUtil {
	public final static String ENCODING = "utf-8";

	public static String hmacMd5Hex(String data) throws Exception {
		// 使用随机值作为密钥
		return HmacUtils.hmacMd5Hex(RandomStringUtils.random(10), data);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MacUtil.hmacMd5Hex("我我我"));
	}
}
