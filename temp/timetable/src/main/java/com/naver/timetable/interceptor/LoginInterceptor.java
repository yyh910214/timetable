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

import com.naver.timetable.model.User;

/**
 * @author younghan
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		//user검사 후 로그인이 안된 상태면
		User user = (User)session.getAttribute("user");
		if( session.getAttribute("user") == null)	{
			// Login페이지는 예외
			if(request.getRequestURI().equals("/login/login")) return true;
			if(request.getRequestURI().equals("/login/join")) return true;
			session.setAttribute("targetPage", request.getRequestURI());
			response.sendRedirect("/login/login");
			return false;
		}
		//로그인이 되어 있는 경우에는 login 이나 join페이지 접근 못하게 함.
		if(request.getRequestURI().equals("/login/login") ||
			request.getRequestURI().equals("/login/join"))	{
			response.sendRedirect("/lecture/index");
			return false;
		}
//		Method method = (Method)handler;
//		for(Object param : method.getParameterTypes())	{
//			System.out.println(param.getClass().getName());
//			if(param instanceof User)	{
//				System.out.println("qwe");
//			}
//		}
		return true;
	} 
}