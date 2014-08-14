/*
 * @(#)Comment.java $version 2014. 8. 14.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class Comment {
	private String studentNum;
	private String lectureNum;
	private String prof;
	private String text;
	private int point;
	
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
