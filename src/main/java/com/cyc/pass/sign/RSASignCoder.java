package com.cyc.pass.sign;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class RSASignCoder {
	/**
	 * 非对称密钥算法
	 */
	public final static String KEY_ALGORITHM = "RSA";

	public final static String SIGNATURE_ALGORITHM = "SHA512withRSA";

	/**
	 * 密钥长度, 必须是64的倍数， 范围在512~65536之间
	 */
	private final static int KEY_SIZE = 1024;

	private final static String PUBLIC_KEY = "RSAPublicKey";
	private final static String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * 数字签名
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 实例化signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化signature
		signature.initSign(priKey);
		// 更新signature
		signature.update(data);
		// 签名
		return signature.sign();
	}

	/**
	 * 数字校验
	 * 
	 * @param data
	 * @param publicKey
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		// 实例化signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化signature
		signature.initVerify(pubKey);
		// 更新signature
		signature.update(data);
		// 校验
		return signature.verify(sign);
	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥对生成器
		keyPairGenerator.initialize(KEY_SIZE);
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 甲方密钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}

	/**
	 * 获取私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}

	/**
	 * 获取公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> initKey = RSASignCoder.initKey();
		byte[] publicKey = RSASignCoder.getPublicKey(initKey);
		byte[] privateKey = RSASignCoder.getPrivateKey(initKey);
		System.out.println("公钥 ： " + Commonscodec.encode(publicKey));
		System.out.println("私钥 ： " + Commonscodec.encode(privateKey));
		String str = "需要签名文件";

		// 产生签名
		byte[] sign = RSASignCoder.sign(str.getBytes(), privateKey);
		System.out.println("签名 : " + Hex.encodeHexString(sign));
		System.out.println(RSASignCoder.verify(str.getBytes(), publicKey, sign));
	}
}
