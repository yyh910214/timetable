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
	
	public void insertSeason(String year, String season)	{
		Map<String, String> parameterMap = Maps.newHashMap();
		parameterMap.put("year", year);
		parameterMap.put("season", season);
		hufsCubrid.insert("insertSeason", parameterMap);
	}
	
	public int isExistSeason(String year, String season)	{
		Map<String, String> parameterMap = Maps.newHashMap();
		parameterMap.put("year", year);
		parameterMap.put("season", season);
		return (Integer)hufsCubrid.queryForObject("isExistSeason", parameterMap);
	}
}
