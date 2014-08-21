/*
 * @(#)CommentDAO.java $version 2014. 8. 18.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.naver.timetable.model.Comment;
import com.naver.timetable.model.CommentSearchParam;
import com.naver.timetable.model.CommentWithLecture;

/**
 * @author younghan
 */
@Repository
public class CommentDAO {
	@Autowired
	@Qualifier("hufsCubrid")
	SqlMapClientTemplate hufsCubrid;
	
	public Comment getComment(Comment comment)	{
		return (Comment)hufsCubrid.queryForObject("getComment",comment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(CommentSearchParam searchParam)	{
		return hufsCubrid.queryForList("getComments", searchParam);
	}
	
	public void insertComment(Comment comment)	{
		hufsCubrid.insert("insertComment", comment);
	}
	
	public void updateComment(Comment comment)	{
		hufsCubrid.update("updateComment", comment);
	}
	
	public void deleteComment(Comment comment)	{
		hufsCubrid.delete("deleteComment", comment);
	}
	
	public int countOfComment(CommentSearchParam searchParam)	{
		return (Integer)hufsCubrid.queryForObject("countOfComment",searchParam);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentWithLecture> getCommentWithLecture(CommentSearchParam searchParam)	{
		return hufsCubrid.queryForList("getCommentWithLecture", searchParam);
	}
}
