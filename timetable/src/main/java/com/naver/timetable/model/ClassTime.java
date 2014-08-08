/*
 * @(#)ClassTime.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * 학수번호 / 강의 시간 저장용
 * @author younghan
 */
public class ClassTime {
	private String classNum;
	private String weekDay;
	
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
}
