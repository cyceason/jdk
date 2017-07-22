package com.cyc.pass.asymmetricencryption.dh;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.cyc.pass.base64.commonscodec.Commonscodec;

public class DHCoder {
	/**
	 * 非对称密钥算法
	 */
	public final static String KEY_ALGORITHM = "DH";

	/**
	 * 对称密钥算法
	 */
	public final static String SECRET_ALGORITHM = "AES";

	/**
	 * 密钥长度
	 */
	private final static int KEY_SIZE = 512;

	private final static String PUBLIC_KEY = "DHPublicKey";
	private final static String PRIVATE_KEY = "DHPrivateKey";

	/**
	 * 初始化甲方密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> jinitKey() throws Exception {
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥对生成器
		keyPairGenerator.initialize(KEY_SIZE);
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 甲方密钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}

	/**
	 * 初始化乙方密钥
	 * 
	 * @param key
	 *            甲方公钥
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> yinitKey(byte[] key) throws Exception {
		// 解析甲方公钥
		// 转换公钥材料
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 产生公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		// 由甲方公钥构建乙方密钥
		DHParameterSpec dHParamSpec = ((DHPublicKey) pubKey).getParams();
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
		// 初始化密钥对生成器
		keyPairGenerator.initialize(dHParamSpec);
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		// 乙方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 乙方密钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成秘密密钥
		SecretKey secretKey = new SecretKeySpec(key, SECRET_ALGORITHM);
		// 实例化
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		// 初始化， 设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成秘密密钥
		SecretKey secretKey = new SecretKeySpec(key, SECRET_ALGORITHM);
		// 实例化
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		// 初始化， 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 构建密钥
	 * 
	 * @param publicKey
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSecretKay(byte[] publicKey, byte[] privateKey) throws Exception {
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 初始化公钥
		// 密钥材料转换
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
		// 产生公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

		// 初始化私钥
		// 密钥材料转换
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		// 产生私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 实例化
		KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
		// 初始化
		keyAgree.init(priKey);
		keyAgree.doPhase(pubKey, true);
		// 生成本地密钥
		SecretKey secretKey = keyAgree.generateSecret(SECRET_ALGORITHM);
		return secretKey.getEncoded();
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
		// 生成甲方密钥对
		Map<String, Object> jKey = DHCoder.jinitKey();
		byte[] jPublicKey = DHCoder.getPublicKey(jKey);
		byte[] jPrivateKey = DHCoder.getPrivateKey(jKey);
		System.out.println("甲方公钥 ： " + Commonscodec.encode(jPublicKey));
		System.out.println("甲方私钥 ： " + Commonscodec.encode(jPrivateKey));

		// 有甲方公钥，产生乙方密钥对
		Map<String, Object> yKey = DHCoder.yinitKey(jPublicKey);
		byte[] yPublicKey = DHCoder.getPublicKey(yKey);
		byte[] yPrivateKey = DHCoder.getPrivateKey(yKey);
		System.out.println("乙方公钥 ： " + Commonscodec.encode(yPublicKey));
		System.out.println("乙方私钥 ： " + Commonscodec.encode(yPrivateKey));

		// 生成甲方本地对称密钥
		byte[] jsecretKay = DHCoder.getSecretKay(yPublicKey, jPrivateKey);
		// 生成乙方本地对称密钥
		byte[] ysecretKay = DHCoder.getSecretKay(jPublicKey, yPrivateKey);

		System.out.println(jsecretKay.length);
		System.out.println("甲方本地对称密钥 : " + Commonscodec.encode(jsecretKay));
		System.out.println("乙方本地对称密钥 : " + Commonscodec.encode(ysecretKay));

		System.out.println("=======甲方向乙方发送加密数据======");
		String input1 = "密码交换算法";
		System.out.println("原文 ： " + input1);
		System.out.println("=======使用甲方本地对称密钥对数据加密======");
	}
}
