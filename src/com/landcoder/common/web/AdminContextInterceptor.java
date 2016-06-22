package com.landcoder.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.landcoder.common.util.URLHelper;

/**
 * 上下文信息拦截器
 * 包括登录信息、权限信息、站点信息
 * 
 * @author landcoder
 * @company oschina
 */
public class AdminContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = URLHelper.getURI(request);
		if (uri.startsWith("/perm")) {
			
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception {
		super.postHandle(request, response, handler, mav);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
