/*
 * @(#)CategoryBO.java $version 2014. 8. 12.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import com.naver.timetable.dao.CategoryDAO;
import com.naver.timetable.model.CampusMajorEnum;
import com.naver.timetable.model.Category;

/**
 * @author younghan
 */
@Service
public class CategoryBO {
	@Autowired
	CategoryDAO categoryDAO;
	
	/**
	 * 전체 카테고리의 내용을 가져옴
	 * 카테고리를 그룹별로 출력하기 위해서 나눠서 다시 List로 담음.
	 * @return
	 */
	public List<List<Category>> getAllCategoryByGroup()	{
		List<List<Category>> result = Lists.newArrayList();
		for(CampusMajorEnum campusMajor : CampusMajorEnum.values())	{
			Category categoryInfo = new Category();
			categoryInfo.setCategoryCampus(campusMajor.getCampus());
			categoryInfo.setCategoryGubun(campusMajor.getMajorCode());
			result.add(categoryDAO.getAllCategoryByGroup(categoryInfo));
		}
		return result;
	}
	
	public List<Category> getMajorCategories()	{
		return categoryDAO.getMajorCategories();
	}
}
