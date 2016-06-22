package com.landcoder.framework.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

/**
 * action基类
 * @author landcoder
 * @company oschina
 */
public abstract class BaseAction {

	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 返回页面
	 * @param tpl 返回的页面地址
	 * @return
	 */
	public String result(String tpl){
		return "/front/"+tpl;
	}

	/**
	 * 页面重定向
	 * @param uri 重定向地址
	 * @return
	 */
	public String redirect(String uri){
		return "redirect:"+uri;
	}
	
	/**
	 * 内部跳转
	 * @param uri 跳转地址
	 * @return
	 */
	public String forward(String uri){
		return "forward:"+uri;
	}

	/**
	 * 返回ajax状态和数据
	 * @param response
	 * @param status 请求状态
	 * @param obj 请求返回的数据
	 * @return
	 */
	public String ajax(HttpServletResponse response, String status, Object obj){
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			json.put("status", status);
			json.put("data", obj);
			out.print(json.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回ajax状态
	 * @param response
	 * @param status 请求状态
	 * @return
	 */
	public String ajax(HttpServletResponse response, String status){
		return ajax(response, status, null);
	}
}
