/*
 * @(#)CategoryDAO.java $version 2014. 8. 6.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import com.naver.timetable.model.Category;

/**
 * @author younghan
 */
@Repository
public class CategoryDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public List<String> getCatgCode(String campusCode, String majorCode)	{
		Map<String, String> parameterMap = Maps.newHashMap();
		parameterMap.put("campusCode", campusCode);
		parameterMap.put("majorCode", majorCode);
		return hufsCubrid.queryForList("getCatgCode", parameterMap);
	}
	
	public void insertCategories(List<Category> categories)	{
		hufsCubrid.insert("insertCategories", categories);
	}
	
	public List<Category> getAllCategoryByGroup(Category categoryInfo)	{
		return hufsCubrid.queryForList("getAllCategoryByGroup", categoryInfo);
	}
	
	public void clearCategory()	{
		hufsCubrid.delete("clearCategory");
	}
	
	public List<Category> getAllCategory()	{
		return hufsCubrid.queryForList("getAllCategory");
	}
	
	public int getCategoryCount()	{
		return (Integer)hufsCubrid.queryForObject("getCategoryCount");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Category> getMajorCategories()	{
		return hufsCubrid.queryForList("getMajorCategories");
	}
}
