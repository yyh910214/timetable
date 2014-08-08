/*
 * @(#)LectureController.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.timetable.model.ClassInfo;
import com.naver.timetable.model.SearchParam;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "/lecture")
public class LectureController {
	@Autowired
	LectureBO lectureBO;
	
	
	@RequestMapping(value = "/searchLecture")
	@ResponseBody
	public List<ClassInfo> searchLecture(SearchParam searchParam)	{
		return lectureBO.searchLecture(searchParam);
	}
}
