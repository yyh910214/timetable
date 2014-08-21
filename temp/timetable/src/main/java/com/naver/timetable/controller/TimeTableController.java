/*
 * @(#)MyTableController.java $version 2014. 8. 21.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.TimeTableBO;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "/timetable")
public class TimeTableController {
	@Autowired
	TimeTableBO timetableBO;
	
	@RequestMapping(value = "/viewTimetable")
	public ModelAndView viewTimeTable(HttpServletRequest request, String year, String season)	{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		List<Lecture> lectureList = timetableBO.getTimeTable(user.getStudentNum(), year, season);
		return new ModelAndView("timetable").addObject("lectureList",lectureList);
	}
	
	@RequestMapping(value = "addTimetable")
	public ModelAndView addTimetable(HttpServletRequest request, String year, String season, String lectureID)	{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		timetableBO.addTimeTable(user.getStudentNum(), lectureID, year, season);
		return new ModelAndView("redirect:/lecture/index");
	}
	
}
