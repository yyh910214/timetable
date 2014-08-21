/*
 * @(#)UserBO.java $version 2014. 8. 14.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.timetable.dao.UserDAO;
import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;
import com.naver.timetable.model.UserSearchParam;

/**
 * @author younghan
 */
@Service
public class UserBO {
	@Autowired
	UserDAO userDAO;
	
	public List<User> getUsers(UserSearchParam searchParam)	{
		searchParam.setTotalRow(userDAO.countOfUser(searchParam));
		searchParam.init();
		
		return userDAO.getUsers(searchParam);
	}
	
	public User getUser(String email)	{
		return userDAO.getUser(email);
	}
	
	public void editUser(User user)	{
		userDAO.editUser(user);
	}
	
	public void changePasswd(LoginInfo changeInfo)	{
		userDAO.changePasswd(changeInfo);
	}
}
