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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.LectureBO;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.SearchParam;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "/lecture")
public class LectureController {
	@Autowired
	LectureBO lectureBO;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request)	{
		return new ModelAndView("lectureIndex");
	}
	
	@RequestMapping(value = "/searchLecture", produces=	{"text/html", "application/json"})
	@ResponseBody
	public List<Lecture> searchLecture(@ModelAttribute SearchParam searchParam)	{
//		System.out.println(searchParam.getCategory().length);
//		return lectureBO.searchLecture(searchParam);
//		System.out.println(category.length);
//		System.out.println(category[0]);
		System.out.println(searchParam.getCyber());
		return null;
	}
}
