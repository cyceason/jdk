package com.cyc.pass.dc;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;

public class CertificateCoder {
	public static final String CERT_TYPE = "X.509";

	/**
	 * 由keystore获取私钥
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKeyBykeyStore(String keyStorePath, String alias, String password)
			throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, password);
		return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}

	/**
	 * 由Certificate获取公钥
	 * 
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKeyByCertificate(String certificatePath) throws Exception {
		// 通过证书路径certificatePath获得Certificate
		Certificate certificate = getCertificate(certificatePath);
		return certificate.getPublicKey();

	}

	/**
	 * 通过证书路径certificatePath获得Certificate
	 * 
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String certificatePath) throws Exception {
		CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);
		FileInputStream is = new FileInputStream(certificatePath);
		Certificate certificate = certificateFactory.generateCertificate(is);
		is.close();
		return certificate;
	}

	/**
	 * 通过keystore 获得Certificate
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, password);
		return ks.getCertificate(alias);
	}

	/**
	 * 获取keystore
	 * 
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream is = new FileInputStream(keyStorePath);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password)
			throws Exception {
		PrivateKey privateKey = getPrivateKeyBykeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password)
			throws Exception {
		PrivateKey privateKey = getPrivateKeyBykeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 签名
	 * 
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(byte[] data, String keyStorePath, String alias, String password) throws Exception {
		// 通过keystore 获得Certificate，即证书
		X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath, alias, password);
		// 构建签名，有证书指定签名算法
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		PrivateKey privateKey = getPrivateKeyBykeyStore(keyStorePath, alias, password);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	public static boolean verify(byte[] data, byte[] sign, String certificatePath) throws Exception {
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
		// 实例化signature
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		// 初始化signature
		signature.initVerify(x509Certificate);
		// 更新signature
		signature.update(data);
		// 校验
		return signature.verify(sign);
	}

	public static void main(String[] args) throws Exception {
		test3();
	}

	private static void test1() throws Exception {
		String password = "123456";
		String alias = "www.cyc.org";
		String certificatePath = "C:\\Users\\cyc_e\\cyc.cer";
		String keyStorePath = "C:\\Users\\cyc_e\\cyc.keystore";
		System.out.println("公钥加密---私钥解密");
		String str = "待加密";
		byte[] encryptByPublicKey = CertificateCoder.encryptByPublicKey(str.getBytes(), certificatePath);
		System.out.println(
				new String(CertificateCoder.decryptByPrivateKey(encryptByPublicKey, keyStorePath, alias, password)));
	}

	private static void test2() throws Exception {
		String password = "123456";
		String alias = "www.cyc.org";
		String certificatePath = "C:\\Users\\cyc_e\\cyc.cer";
		String keyStorePath = "C:\\Users\\cyc_e\\cyc.keystore";
		System.out.println("私钥加密---公钥解密");
		String str = "待加密";
		byte[] encryptByPrivateKey = CertificateCoder.encryptByPrivateKey(str.getBytes(), keyStorePath, alias,
				password);
		System.out.println(new String(CertificateCoder.decryptByPublicKey(encryptByPrivateKey, certificatePath)));
	}

	private static void test3() throws Exception {
		String password = "123456";
		String alias = "www.cyc.org";
		String certificatePath = "C:\\Users\\cyc_e\\cyc.cer";
		String keyStorePath = "C:\\Users\\cyc_e\\cyc.keystore";
		System.out.println("私钥签名---公钥验证");
		String str = "待签名";
		byte[] sign = CertificateCoder.sign(str.getBytes(), keyStorePath, alias, password);
		System.out.println(CertificateCoder.verify(str.getBytes(), sign, certificatePath));
	}
}
