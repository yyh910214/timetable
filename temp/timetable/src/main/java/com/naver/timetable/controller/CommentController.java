/*
 * @(#)CommentController.java $version 2014. 8. 14.
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

import com.naver.timetable.bo.CommentBO;
import com.naver.timetable.bo.LectureBO;
import com.naver.timetable.model.Comment;
import com.naver.timetable.model.CommentSearchParam;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController {
	@Autowired
	CommentBO commentBO;
	
	@Autowired
	LectureBO lectureBO;
	
	@RequestMapping(value = "commentList")
	public ModelAndView commentList(CommentSearchParam searchParam)	{
		ModelAndView mv = new ModelAndView("commentList");
		List<Comment> comments = commentBO.getComments(searchParam);
		Lecture commentLecture = lectureBO.getLecture(searchParam.getLectureID());
		mv.addObject("comments", comments);
		mv.addObject("commentLecture", commentLecture);
		mv.addObject("searchParam",searchParam);
		return mv;
	}
	
	@RequestMapping(value = "commentForm")
	public ModelAndView commentForm(HttpServletRequest request, String lectureID)	{
		ModelAndView mv = new ModelAndView("commentForm");
		HttpSession session = request.getSession();
		Comment comment = commentBO.getComment((User)session.getAttribute("user"), lectureID);
		Lecture commentLecture = lectureBO.getLecture(lectureID);
		if (comment != null)	{
			// 기존에 해당 과목에 대한 댓글을 달았던 경우
			mv.addObject("comment",comment);
		}
		mv.addObject("commentLecture", commentLecture);
		return mv;
	}
	
	@RequestMapping(value = "insert")
	public ModelAndView insertComment(HttpServletRequest request, Comment comment)	{
		commentBO.insertComment(comment);
		return new ModelAndView("redirect:/comment/commentList?lectureID=" + comment.getLectureID());
	}
	
	@RequestMapping(value = "update")
	public ModelAndView updateComment(Comment comment)	{
		commentBO.updateComment(comment);
		return new ModelAndView("redirect:/comment/commentList?lectureID=" + comment.getLectureID());
	}
	
	@RequestMapping(value = "delete")
	public ModelAndView deleteComment(Comment comment)	{
		commentBO.deleteComment(comment);
		return new ModelAndView("redirect:/comment/commentList?lectureID=" + comment.getLectureID());
	}
}
