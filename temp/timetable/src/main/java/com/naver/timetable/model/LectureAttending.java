/*
 * @(#)LectureAttending.java $version 2014. 8. 20.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

import java.sql.Date;



/**
 * @author younghan
 */
public class LectureAttending {
	private String lectureNum;
	private int attending;
	private Date insertDate;
	public String getLectureNum() {
		return lectureNum;
	}
	public void setLectureNum(String lectureNum) {
		this.lectureNum = lectureNum;
	}
	public int getAttending() {
		return attending;
	}
	public void setAttending(int attending) {
		this.attending = attending;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	
}
