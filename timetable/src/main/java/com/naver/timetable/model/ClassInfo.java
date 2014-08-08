/*
 * @(#)ClassInfo.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class ClassInfo {
	private String classNum;
	private String className;
	private String room;
	private String grade;
	private int point;
	private String prof;
	private String cyber;
	private String forNative;
	private String catgId;
	private String url;

	
	public String getCatgId() {
		return catgId;
	}
	public void setCatgId(String catgId) {
		this.catgId = catgId;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
