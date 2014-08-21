/*
 * @(#)ConfigDAO.java $version 2014. 8. 20.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author younghan
 */
@Repository
public class ConfigDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public String getValue(String key)	{
		return (String)hufsCubrid.queryForObject("getConfig", key);
	}
	
	public void insertConfig(String key, String value)	{
		Map<String, String> parameterMap = Maps.newHashMap();
		parameterMap.put("key", key);
		parameterMap.put("value", value);
		hufsCubrid.insert("insertConfig", parameterMap);
	}
}
