/*
 * @(#)TimeTableBO.java $version 2014. 8. 21.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.timetable.dao.LectureDAO;
import com.naver.timetable.dao.TimeTableDAO;
import com.naver.timetable.model.Lecture;

/**
 * @author younghan
 */
@Service
public class TimeTableBO {
	@Autowired
	TimeTableDAO timeTableDAO;
	
	@Autowired
	LectureDAO lectureDAO;
	
	public List<Lecture> getTimeTable(String studentNum, String year, String season)	{
		return timeTableDAO.getTimeTable(studentNum, year, season);
	}
	
	/**
	 * 넣을 강의의 시간 정보를 가져온 후 
	 * 기존 시간표에 있는 강의시간과 겹치는게 있는지 확인.
	 * 겹치는 내용이 있는 경우 false
	 * @param studentNum
	 * @param lectureNum
	 * @param year
	 * @param season
	 * @return
	 */
	public boolean addTimeTable(String studentNum, String lectureID, String year, String season)	{
		List<String> lectureTimes = lectureDAO.getLectureTime(lectureID);	// 넣고자 하는 강의의 시간을 가져옴.
		if ( ((lectureTimes.isEmpty()) ||
			//0일 경우 checkTimeTable은 실행되지 않는다.
			(timeTableDAO.checkTimeTable(studentNum, year, season, lectureTimes) == 0))
			&& (timeTableDAO.isExistLecture(studentNum, lectureID) == 0))	{
			timeTableDAO.addLecture(studentNum, lectureID);
			return true;	
		} 
		return false;
		
	}
}
