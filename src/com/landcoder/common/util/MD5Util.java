package com.landcoder.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author landcoder
 * @company oschina
 */
public class MD5Util {

	public static String encode(String value) {
		try {
			StringBuilder sb = new StringBuilder();
			MessageDigest md = MessageDigest.getInstance("md5");// sha
			byte[] bs = value.getBytes();
			byte[] mb = md.digest(bs);
			for (int i = 0; i < mb.length; i++) {
				int v = mb[i] & 0xFF;// 0~255
				if (v < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(v).toUpperCase());
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static String encode16(String value) {
		try {
			StringBuilder sb = new StringBuilder();
			MessageDigest md = MessageDigest.getInstance("md5");// sha
			byte[] bs = value.getBytes();
			byte[] mb = md.digest(bs);
			for (int i = 0; i < mb.length; i++) {
				int v = mb[i] & 0xFF;// 0~255
				if (v < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(v));
			}
			return sb.substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}
}
