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
import com.naver.timetable.model.SearchParam;

/**
 * @author younghan
 */
@Service
public class LectureBO {
	@Autowired
	LectureDAO lectureDAO;
	public List<Lecture> searchLecture(SearchParam searchParam)	{
		return lectureDAO.searchLecture(searchParam);
	}
}
