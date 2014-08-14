/*
 * @(#)CategoryController.java $version 2014. 8. 12.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.timetable.bo.CategoryBO;

/**
 * @author younghan
 */
@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	CategoryBO categoryBO;

}
