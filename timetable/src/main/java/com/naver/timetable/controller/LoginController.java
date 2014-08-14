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
		if (loginBO.login(loginInfo, request))	{
			return new ModelAndView("lectureIndex");
		} else {
			return  new ModelAndView("login").addObject("retryMessage", retryMessage);
		}
	}
	
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request)	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)	{
			session.removeAttribute("user");
		}
		return new ModelAndView("login").addObject("logoutMessage", logoutMessage);
	}
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request)	{
		return new ModelAndView("join");
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request, User user, String passwd)	{
		loginBO.join(request, user, passwd);
		return new ModelAndView("lectureIndex");
	}
	
	@RequestMapping(value="searchPasswd", method=RequestMethod.GET)
	public ModelAndView searchPasswd(Model model)	{
		return new ModelAndView("searchPasswd");
	}
	@RequestMapping(value="changePasswd")
	public void changePasswd(Model model)	{
		
	}
}
