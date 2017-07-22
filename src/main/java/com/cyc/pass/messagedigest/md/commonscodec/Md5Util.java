package com.cyc.pass.messagedigest.md.commonscodec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class Md5Util {
	public final static String ENCODING = "utf-8";

	public static String md5(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		return Commonscodec.encode(DigestUtils.md5(str));
	}

	public static String md5Hex(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		// 获取32位十六进制字符串
		return DigestUtils.md5Hex(str);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Md5Util.md5("我我我"));
		System.out.println(Md5Util.md5Hex("我我我"));
	}
}
