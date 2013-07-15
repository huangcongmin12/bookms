package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Encrypt {

	public static String encodeMD5(String str) {
		if (null == str) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest code = MessageDigest.getInstance("MD5");
			code.update(str.getBytes());
			byte[] bs = code.digest();
			for (int i = 0; i < bs.length; i++) {
				int v = bs[i] & 0xFF;
				if (v < 16) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(v));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString().toUpperCase();
	}

	public static boolean isValidInput(String str) {
		return str.matches("[a-z0-9]+");
	}

}
