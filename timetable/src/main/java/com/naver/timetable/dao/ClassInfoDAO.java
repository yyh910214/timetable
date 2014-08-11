/*
 * @(#)ClassInfoDAO.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.ClassTime;

/**
 * @author younghan
 */
@Repository
public class ClassInfoDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public void saveClassInfoList(List<Lecture> classInfos)	{
		hufsCubrid.insert("saveClassInfoList", classInfos);
	}
	
	public void saveClassTimeList(List<ClassTime> timeList)	{
		hufsCubrid.insert("saveTimeList", timeList);
	}
	
	public void clearLecture()	{
		hufsCubrid.delete("clearLecture");
	}
	
	public void clearLectureSche()	{
		hufsCubrid.delete("clearLectureSche");
	}
}
