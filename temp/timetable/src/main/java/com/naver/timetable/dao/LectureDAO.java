/*
 * @(#)LectureDAO.java $version 2014. 8. 11.
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
import com.naver.timetable.model.LectureAttending;
import com.naver.timetable.model.LectureSearchParam;
import com.naver.timetable.model.LectureTime;

/**
 * @author younghan
 */
@Repository
public class LectureDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public List<Lecture> searchLecture(LectureSearchParam searchParam)	{
		return hufsCubrid.queryForList("searchLecture", searchParam);
	}
	
	public void saveClassInfoList(List<Lecture> classInfos)	{
		hufsCubrid.insert("saveLectureList", classInfos);
	}
	
	public void saveClassTimeList(List<LectureTime> timeList)	{
		hufsCubrid.insert("saveTimeList", timeList);
	}
	
	public void clearLecture()	{
		hufsCubrid.delete("clearLecture");
	}
	
	public void clearLectureSche()	{
		hufsCubrid.delete("clearLectureSche");
	}
	
	public void saveAttending(List<LectureAttending> attendingList)	{
		hufsCubrid.insert("saveAttending", attendingList);
	}

	public List<LectureAttending> getAttendingList(String lectureNum) {
		return hufsCubrid.queryForList("getLectureAttending", lectureNum);
	}
	
	public List<String> getLectureTime(String lectureID)	{
		return hufsCubrid.queryForList("getLectureTime", lectureID);
	}
	
	public int getLastID()	{
		if((Integer)hufsCubrid.queryForObject("getLastID") == null)
			return 0;
		return (Integer)hufsCubrid.queryForObject("getLastID");
	}
	
	public Lecture getLecture(String lectureID)	{
		return (Lecture)hufsCubrid.queryForObject("getLecture", lectureID);
	}
}
