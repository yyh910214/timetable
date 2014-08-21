/*
 * @(#)EditAuthorizationInterceptor.java $version 2014. 8. 18.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.naver.timetable.model.User;

/**
 * @author younghan
 */
public class EditAuthorizationInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 세션의 학번과 수정하고자 하는 글의 학번이 같은지 비교하여 다를경우 실패
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)request.getSession().getAttribute("user");
		if(user.getStudentNum().equals(request.getParameter("studentNum")) ||
			(user.getUserLevel() == 1))	{	
			return true;
		} else	{
			PrintWriter out = response.getWriter();  
			response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('no permission');");
			out.println("location.href='"+ request.getHeader("Referer") +"'");
			out.println("</script>");
			out.flush();
			return false;
		}
	}

}
