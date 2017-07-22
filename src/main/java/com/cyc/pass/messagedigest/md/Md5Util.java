package com.cyc.pass.messagedigest.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class Md5Util {
	public final static String ENCODING = "utf-8";

	public static String md5(String str, String charsetName) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 加密后，再通过 base64 编码， 因为编码后是二进制，base64正好用来编码二进制
		return Commonscodec.encode(md.digest(str.getBytes(charsetName)));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Md5Util.md5("我我我", ENCODING));
	}
}
