package com.cyc.pass.messagedigest.sha.commonscodec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class SHA1Util {
	public final static String ENCODING = "utf-8";

	public static String sha1(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		return Commonscodec.encode(DigestUtils.sha1(str));
	}

	public static String sha1Hex(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		// 获取40位十六进制字符串
		return DigestUtils.sha1Hex(str);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(SHA1Util.sha1("我我我"));
		System.out.println(SHA1Util.sha1Hex("我我我"));
	}
}
