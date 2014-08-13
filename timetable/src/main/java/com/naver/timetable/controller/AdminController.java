/*
 * @(#)AdminController.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.timetable.bo.TableParsingBO;

/**
 * 관리자 페이지용 controller
 * @author younghan
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	TableParsingBO tableParsingBO;
	
	@RequestMapping(value = "/index")
	public ModelAndView	index(Model model)	{
//		return new ModelAndView("index");
		return new ModelAndView("searchFilter");
	}
	
	@RequestMapping(value = "/tableParsing")
	public ModelAndView tableParsing(Model model)	{
//		tableParsingBO.saveCategory("2014","3");
		tableParsingBO.saveTimeTable("2014","3");
		return new ModelAndView("redirect:/admin/index");
	}

}
