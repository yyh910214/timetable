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
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request)	{
		ModelAndView mv = new ModelAndView("lectureIndex");
		mv.addObject("categoryGroup", categoryBO.getAllCategoryByGroup());
		mv.addObject("weekdays", TableParsingBO.WEEKDAY);/////////////
		return mv;
	}
	
	@RequestMapping(value="test")
	public ModelAndView test(HttpServletRequest request)	{
		return new ModelAndView("maindecorators");
	}
	
	@RequestMapping(value = "/searchLecture", produces=	{"text/html", "application/json"},headers = {"Content-type=application/json"})
	@ResponseBody
	public List<Lecture> searchLecture(HttpServletRequest request, @RequestBody LectureSearchParam searchParam)	{
		//연도와 학기도 페이지에서 같이 넘겨받아야 함
		searchParam.setLectureYear("2014");
		searchParam.setLectureSeason("3");
		System.out.println(null == searchParam.getCategory());
		System.out.println(null == searchParam.getPoint());
		System.out.println(null == searchParam.getSchedule());
System.out.println(searchParam.getSchedule().size());
		System.out.println(searchParam.getCyber());
		System.out.println(searchParam.getForNative());
		return lectureBO.searchLecture(searchParam);
	}
	
	@RequestMapping(value = "/attendingView")
	public ModelAndView attendingView(String lectureNum)	{
		return new ModelAndView("attendingView").addObject("attendingList", lectureBO.getAttendingList(lectureNum));
	}
}
