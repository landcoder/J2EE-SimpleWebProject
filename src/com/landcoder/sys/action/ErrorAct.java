package com.landcoder.sys.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landcoder.framework.base.BaseAction;

/**
 * 错误页面
 * @author landcoder
 * @company oschina
 */
@Controller
public class ErrorAct extends BaseAction {

	/**
	 * 返回错误页面
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/error.html")
	public String error(HttpServletRequest request) {
		String code = request.getParameter("errorCode");
		return "/error/" + code + ".html";
	}
}
