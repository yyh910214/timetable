/*
 * @(#)LoginController.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.CategoryBO;
import com.naver.timetable.bo.LoginBO;
import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value="login")
public class LoginController {
	@Value("#{error['retryMessage']}")
	private String retryMessage;
	@Value("#{error['logoutMessage']}")
	private String logoutMessage;
	
	@Autowired
	LoginBO loginBO;
	
	@Autowired
	CategoryBO categoryBO;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView login(Model model) 	{
		return new ModelAndView("login");
	}
	
	/**
	 * 로그인 처리용 메소드
	 * @param loginInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(LoginInfo loginInfo, HttpServletRequest request)	{
		HttpSession session = request.getSession();
		ModelAndView mv;
		switch(loginBO.login(loginInfo, request))	{
			case LoginBO.LOGIN_SUCCESS :
				String targetPage = session.getAttribute("targetPage") == null ? "/lecture/index" : (String)session.getAttribute("targetPage"); 
				mv = new ModelAndView("redirect:"+ targetPage);
				break;
				
				//처음 이용하는 사용자의 경우
			case LoginBO.FIRST_LOGIN :
				mv = new ModelAndView("join");
				mv.addObject("majorCategories", categoryBO.getMajorCategories());
				mv.addObject("studentNum", loginInfo.getStudentNum());
				break;
				
			case LoginBO.LOGIN_FAILED :
			default :
				mv = new ModelAndView("login").addObject("retryMessage", retryMessage);
		}
		return mv;
	}
	
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request)	{
		loginBO.logout(request);
		return new ModelAndView("login").addObject("logoutMessage", logoutMessage);
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request, User user)	{
		System.out.println(user.getStudentNum());
		loginBO.join(user, request);
		return new ModelAndView("redirect:/lecture/index");
	}

}
