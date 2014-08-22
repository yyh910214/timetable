/*
 * @(#)CommentBO.java $version 2014. 8. 18.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.timetable.dao.CommentDAO;
import com.naver.timetable.model.Comment;
import com.naver.timetable.model.CommentSearchParam;
import com.naver.timetable.model.CommentWithLecture;
import com.naver.timetable.model.User;

/**
 * @author younghan
 */
@Service
public class CommentBO {
	@Autowired
	CommentDAO commentDAO;
	
	/**
	 * 유저가 기존에 작성한 평가를 불러옴
	 * @param user
	 * @param lectureID
	 * @return
	 */
	public Comment getComment(User user, String lectureID)	{
		Comment comment = new Comment();
		comment.setLectureID(lectureID);
		comment.setStudentNum(user.getStudentNum());
		return commentDAO.getComment(comment);
	}
	
	public void insertComment(Comment comment)	{
		commentDAO.insertComment(comment);
	}
	
	public void updateComment(Comment comment)	{
		commentDAO.updateComment(comment);
	}
	
	public void deleteComment(Comment comment)	{
		commentDAO.deleteComment(comment);
	}
	
	public List<CommentWithLecture> getCommentWithLecture(CommentSearchParam searchParam)	{
		searchParam.setTotalRow(commentDAO.countOfComment(searchParam));
		searchParam.init();
		return commentDAO.getCommentWithLecture(searchParam);
	}
}
