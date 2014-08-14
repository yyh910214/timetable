/*
 * @(#)PageInfo.java $version 2014. 8. 14.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.model;

/**
 * @author younghan
 */
public class PageInfo {
	private int totalPage;
	private int currentPage = 1;
	private int pageSize = 10;
	private int totalRow;
	private int startRowNum;
	private int endRowNum;
	
	public void init()	{
		initTotalPage();
		initRowNum();
	}
	public void init(int totalRow, int pageSize, int currentPage)	{
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		initTotalPage();
		initRowNum();
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	
	protected void initTotalPage()	{
		if (this.totalRow % this.pageSize == 0)	{
			this.totalPage = (this.totalRow / this.pageSize);
		}
		else	{
			this.totalPage = (this.totalRow / this.pageSize) + 1;	
		}
	}
	
	protected void initRowNum()	{
		startRowNum = this.pageSize * (this.currentPage - 1) + 1;
		endRowNum = this.pageSize * this.currentPage;
	}

	
}
