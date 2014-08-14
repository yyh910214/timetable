/*
 * @(#)LoginInterceptor.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author younghan
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		//user검사 후 로그인이 안된 상태면
		if( session.getAttribute("user") == null)	{
			// Login페이지는 예외
			if(request.getRequestURI().equals("/login/login")) return true;
			if(request.getRequestURI().equals("/login/join")) return true;
			response.sendRedirect("/login/login");
			return false;
		}
		return true;
	} 
}