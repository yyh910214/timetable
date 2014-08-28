/*
 * @(#)LectureController.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.CategoryBO;
import com.naver.timetable.bo.LectureBO;
import com.naver.timetable.bo.TableParsingBO;
import com.naver.timetable.bo.ThreadTestBO;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.LectureSearchParam;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "/lecture")
public class LectureController {
	@Autowired
	LectureBO lectureBO;
	
	@Autowired
	CategoryBO categoryBO;
	
	@Autowired
	ThreadTestBO threadTestBO;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request)	{
		ModelAndView mv = new ModelAndView("lectureIndex");
		mv.addObject("categoryGroup", categoryBO.getAllCategoryByGroup());
		mv.addObject("weekdays", TableParsingBO.WEEKDAY);
		return mv;
	}
	
	@RequestMapping(value = "/searchLecture", produces=	{"text/html", "application/json"})
	@ResponseBody
	public List<Lecture> searchLecture(HttpServletRequest request, @RequestBody LectureSearchParam searchParam)	{
		//연도와 학기도 페이지에서 같이 넘겨받아야 함
		return lectureBO.searchLecture(searchParam);
	}
	
	@RequestMapping(value = "/attendingView")
	public ModelAndView attendingView(String lectureNum)	{
		return new ModelAndView("attendingView").addObject("attendingList", lectureBO.getAttendingList(lectureNum));
	}
	
	@RequestMapping(value = "testpage")
	public ModelAndView testPage()	{
		return new ModelAndView("testpage");
	}
}
