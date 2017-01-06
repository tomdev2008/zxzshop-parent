/*
 * @auth 朱飞
 * @(#)EncryptUtil.java 2016-11-3上午9:24:56
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 *
 * @author 朱飞 
 * [2016-11-3上午9:24:56] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public class EncryptUtil {

	public static String private_key = "D:\\unionkey\\8582_dongfangjiancaiwang.key.p8";
	public static String public_key = "D:\\unionkey\\cert_2d59.crt";
	
	/**
	 * 加密接口
	 * @author 朱飞
	 * @creationDate. 2016-11-3 上午9:31:02 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content) throws Exception {
		X509Certificate cert = getCert(getKey(true));
		byte[] keyBytes = cert.getPublicKey().getEncoded();
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(1, publicKey);
		String endDate = "";
		// 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
		while(content.length() > 64){
			String tempStr = content.substring(0, 64);
			byte[] doFinal = cipher.doFinal(tempStr.getBytes());
			endDate += Base64.getBASE64(doFinal).replace("\n", "");
			content = content.substring(tempStr.length());
		}
		endDate +=  Base64.getBASE64(cipher.doFinal(content.getBytes())).replace("\n", "");
		return endDate;
	}
	
	
	public static String sign(String plain) {
		try {
			Signature sig = Signature.getInstance("MD5withRSA");
			PrivateKey pk = getPk(getKey(false));
			sig.initSign(pk);
			sig.update(plain.getBytes("gbk"));
			byte[] signData = sig.sign();
			String sign = new String(Base64.getBASE64(signData));
			return sign;
		} catch (Exception ex) {
			RuntimeException rex = new RuntimeException(ex.getMessage());
			rex.setStackTrace(ex.getStackTrace());
			throw rex;
		}
	}
	public static PrivateKey getPk(byte[] b) {
		PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(b);
		RuntimeException rex;
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey pk = kf.generatePrivate(peks);
			return pk;
		} catch (NoSuchAlgorithmException e) {
			rex = new RuntimeException();
			rex.setStackTrace(e.getStackTrace());
			throw rex;
		} catch (InvalidKeySpecException e) {
			rex = new RuntimeException();
			rex.setStackTrace(e.getStackTrace());
			throw rex;
		}
	}

	private static X509Certificate getCert(byte[] b) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate x509Certificate = (X509Certificate) cf
				.generateCertificate(bais);
		return x509Certificate;
	}

	private static byte[] getKey(boolean pub) throws Exception {
		String path = null;
		if(pub){
			path = public_key;
		}else{
			path = private_key;
		}
		FileInputStream fis = new FileInputStream(new File(path));
		byte[] bt = new byte[2048];
		fis.read(bt);
		fis.close();
		return bt;
	}
}
