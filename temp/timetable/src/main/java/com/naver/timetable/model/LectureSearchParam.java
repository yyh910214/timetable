/*
 * @(#)SearchParam.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

import java.util.List;

/**
 * @author younghan
 */
public class LectureSearchParam extends PageInfo{
	private String lectureYear;
	private String lectureSeason;
	private List<String> point;
	private List<String> category;
	private List<String> schedule;
	private String cyber;
	private String forNative;

	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<String> getPoint() {
		return point;
	}
	public void setPoint(List<String> point) {
		this.point = point;
	}
	

	public String getLectureYear() {
		return lectureYear;
	}
	public void setLectureYear(String lectureYear) {
		this.lectureYear = lectureYear;
	}
	public String getLectureSeason() {
		return lectureSeason;
	}
	public void setLectureSeason(String lectureSeason) {
		this.lectureSeason = lectureSeason;
	}
	public List<String> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
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
