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
import com.naver.timetable.model.PageInfo;
import com.naver.timetable.model.User;
import com.naver.timetable.model.UserSearchParam;

/**
 * @author younghan
 */
@Service
public class UserBO {
	@Autowired
	UserDAO userDAO;
	
	public List<User> getUsers(UserSearchParam searchParam, PageInfo pageInfo)	{
		pageInfo.setTotalRow(userDAO.countOfUser(searchParam));
		pageInfo.init();
		searchParam.setStartRowNum(pageInfo.getStartRowNum());
		searchParam.setEndRowNum(pageInfo.getEndRowNum());
		
		return userDAO.getUsers(searchParam);
	}
}
