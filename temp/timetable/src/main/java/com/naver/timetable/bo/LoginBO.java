/*
 * @(#)LoginBO.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.timetable.dao.UserDAO;
import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Service
public class LoginBO {
	@Autowired
	UserDAO userDAO;
	
	public boolean login(LoginInfo loginInfo, HttpServletRequest request)	{
		User user = userDAO.login(loginInfo);
		if(user != null)	{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return true;
		} else {	
			return false;
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
	public void join(HttpServletRequest request, User user, String passwd)	{
		userDAO.join(user);
		changePasswd(user.getEmail(),passwd);
	}
	
	public void changePasswd(String email, String passwd)	{
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setEmail(email);
		loginInfo.setPasswd(passwd);
		userDAO.changePasswd(loginInfo);
	}
}
