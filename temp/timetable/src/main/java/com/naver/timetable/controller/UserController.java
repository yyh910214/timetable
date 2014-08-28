/*
 * @(#)UserController.java $version 2014. 8. 18.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.CommentBO;
import com.naver.timetable.bo.TimeTableBO;
import com.naver.timetable.bo.UserBO;
import com.naver.timetable.model.CommentSearchParam;
import com.naver.timetable.model.CommentWithLecture;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;
import com.naver.timetable.resolver.SessionUser;
@Controller
@RequestMapping(value ="/user")
public class UserController {
	@Autowired
	UserBO userBO;
	
	@Autowired
	CommentBO commentBO;
	
	@Autowired
	TimeTableBO timeTableBO;
	
	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(HttpServletRequest request, String email)	{
		ModelAndView mv = new ModelAndView("userEditForm");
		mv.addObject("user", userBO.getUser(email));
		
		// 어느 페이지로 부터 왔는지 세션에 저장.
		HttpSession session = request.getSession();
		session.setAttribute("targetPage", request.getHeader("Referer"));
		return mv;
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, User user, String passwd)	{
		userBO.editUser(user);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setEmail(user.getEmail());
		loginInfo.setPasswd(passwd);
		if(!passwd.equals(""))	{
			userBO.changePasswd(loginInfo);
		}
		
		HttpSession session = request.getSession();
		String targetPage = session.getAttribute("targetPage") == null ? "/lecture/index" : (String)session.getAttribute("targetPage");
		return new ModelAndView(new StringBuilder("redirect:").append(targetPage).toString());
	}
	
	@RequestMapping(value = "/myPage")
	public ModelAndView myPage(@SessionUser User user, CommentSearchParam searchParam)	{
		ModelAndView mv = new ModelAndView("myPage");
		searchParam.setStudentNum(user.getStudentNum());
		List<CommentWithLecture> comments = commentBO.getCommentWithLecture(searchParam);
		List<Lecture> timetable = timeTableBO.getTimeTable(user.getStudentNum(), "2014", "3");
		mv.addObject("comments", comments);
		mv.addObject("searchParam", searchParam);
		mv.addObject("timetable", timetable);
		return mv;
	}
	
}
