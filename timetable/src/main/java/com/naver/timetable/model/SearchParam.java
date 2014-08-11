/*
 * @(#)SearchParam.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class SearchParam {
	private String[] point;
	private String[] category;
	private String[] lectureSche;
	private String cyber;
	private String forNative;
	
	public String[] getPoint() {
		return point;
	}
	public void setPoint(String[] point) {
		this.point = point;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public String[] getLectureSche() {
		return lectureSche;
	}
	public void setLectureSche(String[] lectureSche) {
		this.lectureSche = lectureSche;
	}
	public String getCyber() {
		return cyber;
	}
	public void setCyber(String cyber) {
		this.cyber = cyber;
	}
	public String getForNative() {
		return forNative;
	}
	public void setForNative(String forNative) {
		this.forNative = forNative;
	}
}
