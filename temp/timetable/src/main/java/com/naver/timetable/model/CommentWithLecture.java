/*
 * @(#)CommentWithLecture.java $version 2014. 8. 21.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class CommentWithLecture extends Comment{
	private String lectureName;
	private String lectureNum;
	private String prof;
	private String lecture_year;
	private String lecture_season;
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getLectureNum() {
		return lectureNum;
	}
	public void setLectureNum(String lectureNum) {
		this.lectureNum = lectureNum;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getLecture_year() {
		return lecture_year;
	}
	public void setLecture_year(String lecture_year) {
		this.lecture_year = lecture_year;
	}
	public String getLecture_season() {
		return lecture_season;
	}
	public void setLecture_season(String lecture_season) {
		this.lecture_season = lecture_season;
	}
	
	
}
