/*
 * @(#)LectureBO.java $version 2014. 8. 11.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.timetable.dao.LectureDAO;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.LectureAttending;
import com.naver.timetable.model.LectureSearchParam;

/**
 * @author younghan
 */
@Service
public class LectureBO {
	@Autowired
	LectureDAO lectureDAO;
	
	public List<Lecture> searchLecture(LectureSearchParam searchParam)	{
		return lectureDAO.searchLecture(searchParam);
	}
	
	public List<LectureAttending> getAttendingList(String lectureNum)	{
		return lectureDAO.getAttendingList(lectureNum);
	}
	
	public Lecture getLecture(String lectureID)	{
		return lectureDAO.getLecture(lectureID);
	}
	
}
