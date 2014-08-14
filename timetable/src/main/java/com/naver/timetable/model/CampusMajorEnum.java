/*
 * @(#)CampusMajorEnum.java $version 2014. 8. 13.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public enum CampusMajorEnum {
	SEOUL_MAJOR("H1","1","crs_strct_cd"),SEOUL_CULTURAL("H1","2","compt_fld_cd"),
	YONGIN_MAJOR("H2","1","crs_strct_cd"),YONGIN_CULTURAL("H2","2","compt_fld_cd");
	
	
	String campus;
	String majorCode;
	String majorUrl;
	
	CampusMajorEnum(String campus, String majorCode, String majorUrl)	{
		this.campus = campus;
		this.majorCode = majorCode;
		this.majorUrl = majorUrl;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMajorUrl() {
		return majorUrl;
	}

	public void setMajorUrl(String majorUrl) {
		this.majorUrl = majorUrl;
	}
}
