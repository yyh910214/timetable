/*
 * @(#)UrlDAO.java $version 2014. 8. 6.
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

/**
 * @author younghan
 */
@Repository
public class UrlDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public List<String> getAllParam()	{
		return hufsCubrid.queryForList("getAllParam");
	}
}
