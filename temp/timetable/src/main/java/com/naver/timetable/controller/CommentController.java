/*
 * @(#)CommentController.java $version 2014. 8. 14.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController {
	@Autowired
	
	@RequestMapping(value = "insertCommentForm")
	public ModelAndView insertCommentForm(int )	{
		
	}
}
