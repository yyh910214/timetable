/*
 * @(#)TimeTableDAO.java $version 2014. 8. 21.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import com.naver.timetable.model.Lecture;

/**
 * @author younghan
 */
@Repository
public class TimeTableDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	/**
	 * 해당 학기 학생의 강의 시간표를 가져옴.
	 * @param studentNum
	 * @param year
	 * @param season
	 * @return
	 */
	public List<Lecture> getTimeTable(String studentNum, String year, String season)	{
		Map<String, Object> parameterMap = Maps.newHashMap();
		parameterMap.put("studentNum", studentNum);
		parameterMap.put("lecture_year", year);
		parameterMap.put("lecture_season", season);
		return hufsCubrid.queryForList("getTimeTable", parameterMap);
	}
	
	
	//3개의 테이블. JOIN
	public int checkTimeTable(String studentNum, String year, String season, List<String> lectureTimes)	{
		Map<String, Object> parameterMap = Maps.newHashMap();
		parameterMap.put("studentNum", studentNum);
		parameterMap.put("lectureTimes", lectureTimes);
		parameterMap.put("lecture_year", year);
		parameterMap.put("lecture_season", season);
		return (Integer)hufsCubrid.queryForObject("checkTimeTable", parameterMap);
	}
	
	public void addLecture(String studentNum, String lectureID)	{
		Map<String, Object> parameterMap = Maps.newHashMap();
		parameterMap.put("studentNum", studentNum);
		parameterMap.put("lectureID", lectureID);
		hufsCubrid.insert("addLecture", parameterMap);
	}
	
	/**
	 * 기존에 동일한 내용의 시간표 목록이 있나 확인하기 위함.
	 * @param studentNum
	 * @param lectureID
	 * @return
	 */
	public int isExistLecture(String studentNum, String lectureID)	{
		Map<String, Object> parameterMap = Maps.newHashMap();
		parameterMap.put("studentNum", studentNum);
		parameterMap.put("lectureID", lectureID);
		return (Integer)hufsCubrid.queryForObject("isExistLecture", parameterMap);
	}
}
