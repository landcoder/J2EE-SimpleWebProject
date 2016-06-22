package com.landcoder.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * URL工具类
 * @author landcoder
 * @company oschina
 */
public class URLHelper {

	public static String getURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if(uri.indexOf(".html") + 5!=uri.length()){
			uri = uri.substring(0, uri.indexOf(".html") + 5);
		}
		String ctx = request.getContextPath();
		if (!StringUtils.isBlank(ctx)) {
			return uri.substring(ctx.length());
		} else {
			return uri;
		}
	}
	
	/**
	 * 获得URI路径参数
	 * 
	 * @param uri URI {@link HttpServletRequest#getRequestURI()}
	 * @return
	 */
	public static String[] getPaths(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri == null) {
			throw new IllegalArgumentException("URI can not be null");
		}
		if (!uri.startsWith("/")) {
			throw new IllegalArgumentException("URI must start width '/'");
		}
		String[] paths = null;
		int gi = uri.lastIndexOf("/");
		int bi = uri.indexOf("_");
		int pi = uri.indexOf(".");
		if (bi != -1) {
			paths = uri.substring(gi + 1, pi).split("_");
		}
		return paths;
	}
}
