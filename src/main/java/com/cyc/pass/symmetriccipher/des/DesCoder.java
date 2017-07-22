package com.cyc.pass.symmetriccipher.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import com.cyc.pass.base64.commonscodec.Commonscodec;
import java.security.Key;

/**
 * 在本例子中，密钥算法是“DES”，加解密算法是“DES/ECB/PKCS5Padding”；如果密钥算法跟加解密算法一样，则工作默认跟填充模式为默认方式
 * 
 * @author cyc_e
 *
 */
public class DesCoder {
	/**
	 * 密钥算法
	 */
	public final static String KEY_ALGORITHM = "DES";

	/**
	 * 加密算法/工作模式/填充方式
	 */
	public final static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		// 实例化密钥生成器
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥生成器
		kg.init(56);
		// 生成密钥， 一个随机密钥
		SecretKey secretKey = kg.generateKey();
		// 获取密钥二进制编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		// 实例化DESKeySpec密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		// 实例秘密化密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		// 生成秘密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
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
		// 还原密钥
		Key k = toKey(key);
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化， 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
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
		// 还原密钥
		Key k = toKey(key);
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化， 设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String str = "原文";
		// 初始化密钥
		byte[] initKey = DesCoder.initKey();
		System.out.println("密钥 ： " + Commonscodec.encode(initKey));
		// 加密
		byte[] encrypt = DesCoder.encrypt(str.getBytes(), initKey);
		System.out.println("加密后 ： " + Commonscodec.encode(encrypt));
		// 解密
		byte[] decrypt = DesCoder.decrypt(encrypt, initKey);
		System.out.println("解密后 ： " + new String(decrypt));

	}

}
