package com.wangmeng.common.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.math.RandomUtils;

/**
 *  MD5加密
 *
 */
public class MD5Util {

	/**
	 * md5加密
	 * 
	 * @param pwd
	 * @return
	 */
	public static final String crypt(String pwd) {
		String p = Md5Crypt.md5Crypt(pwd.getBytes());
		while (p.length() != 34) {
			p = Md5Crypt.md5Crypt(pwd.getBytes());
		}
		return p;
	}

	/**
	 * md5加密
	 * 
	 * @param pwd
	 * @param salt
	 * @return
	 */
	public static final String crypt(String pwd, String salt) {
		String p = Md5Crypt.md5Crypt(pwd.getBytes(), salt);
		while (p.length() != 34) {
			p = Md5Crypt.md5Crypt(pwd.getBytes(), salt);
		}
		return p;
	}

	/**
	 * 是否一致
	 * 
	 * @param p
	 *            字符串
	 * @param e
	 *            加密后字符串
	 * @return
	 */
	public static final boolean isStringEquals(String p, String e) {
		String salt = e.substring(0, 11);
		return e.equals(crypt(p, salt));
	}

	/**
	 * salt
	 * @return
	 */
	public final static String saltCode() {
		String salt = "" + (RandomUtils.nextInt() % 10000);
		return salt;
	}

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
