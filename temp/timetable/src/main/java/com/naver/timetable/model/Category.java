/*
 * @(#)Category.java $version 2014. 8. 6.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class Category {
	private String categoryId;
	private String categoryName;
	private String categoryGubun;
	private String categoryCampus;
	
	public String getCategoryCampus() {
		return categoryCampus;
	}
	public void setCategoryCampus(String categoryCampus) {
		this.categoryCampus = categoryCampus;
	}
	public Category()	{ }
	public Category(String categoryId, String categoryName, String categoryGubun, String categoryCampus)	{
		this.categoryGubun = categoryGubun;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCampus = categoryCampus;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryGubun() {
		return categoryGubun;
	}
	public void setCategoryGubun(String categoryGubun) {
		this.categoryGubun = categoryGubun;
	}
}
