/*
 * @(#)LoginDAO.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.naver.timetable.model.LoginInfo;
import com.naver.timetable.model.User;
import com.naver.timetable.model.UserSearchParam;

/**
 * @author younghan
 */
@Repository
public class UserDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public User login(LoginInfo loginInfo)	{
		return (User)hufsCubrid.queryForObject("login", loginInfo); 
	}
	
	public void join(User user)	{
		hufsCubrid.insert("join", user);
	}
	
	public void setPasswd(LoginInfo loginInfo)	{
		hufsCubrid.update("setPasswd", loginInfo);
	}
	
	public int countOfUser(UserSearchParam searchParam)	{
		return (Integer)hufsCubrid.queryForObject("countOfUser", searchParam);
	}
	
	public List<User> getUsers(UserSearchParam searchParam)	{
		return hufsCubrid.queryForList("getUsers", searchParam);
	}
}
