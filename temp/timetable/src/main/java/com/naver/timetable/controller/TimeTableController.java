/*
 * @(#)MyTableController.java $version 2014. 8. 21.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.TimeTableBO;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.User;
import com.naver.timetable.resolver.SessionUser;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "/timetable")
public class TimeTableController {
	@Autowired
	TimeTableBO timetableBO;
	
	@RequestMapping(value = "/viewTimetable")
	public ModelAndView viewTimeTable(@SessionUser User user, String year, String season)	{		
		List<Lecture> lectureList = timetableBO.getTimeTable(user.getStudentNum(), year, season);
		return new ModelAndView("timetable").addObject("lectureList",lectureList);
	}
	
	@RequestMapping(value = "addTimetable")
	public ModelAndView addTimetable(@SessionUser User user, String year, String season, String lectureID)	{
		if(timetableBO.addTimeTable(user.getStudentNum(), lectureID, year, season))
			return new ModelAndView("success");
		else
			return new ModelAndView("failed");
	}
	
}
