/*
 * @(#)LoginBO.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import com.naver.timetable.dao.UserDAO;
import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Service
public class LoginBO {
	private static final String loginUrl = "https://eclass2.hufs.ac.kr:4443/ilos/lo/login.acl";
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAILED = 2;
	public static final int FIRST_LOGIN = 3;
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpClientBO httpClientBO;
	
	public int login(LoginInfo loginInfo, HttpServletRequest request)	{
		//로그인 성공시 받아오는 페이지
		//http client를 만들어서 http://eclass2.hufs.ac.kr:8181/ilos/lo/login_branch.acl로 요청 보냄
			
		List<NameValuePair> loginParam = Lists.newArrayList();
		loginParam.add(new BasicNameValuePair("usr_id", loginInfo.getStudentNum()));
		loginParam.add(new BasicNameValuePair("usr_pwd", loginInfo.getPasswd()));
		
		// 로그인 성공
		if(httpClientBO.getHttpBody(loginUrl, "POST", loginParam).length() < 1000)	{
			User user = userDAO.login(loginInfo.getStudentNum());
			if(user != null)	{
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				return LOGIN_SUCCESS;
			} else	{
				return FIRST_LOGIN;
			}
		} else	{
			return LOGIN_FAILED;
		}
	}
	
	public void logout(HttpServletRequest request)	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)	{
			session.removeAttribute("user");
		}
	}
	
	/**
	 * 회원 가입을 처리하는 메소드
	 * pw를 유저에 담지 않기 위해서
	 * @param request
	 * @param user
	 * @param pw
	 */
	@Transactional
	public void join(User user, HttpServletRequest request)	{
		
		userDAO.join(user);
		
		user = userDAO.login(user.getStudentNum());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}

}
