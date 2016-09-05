package org.andy.work.paging;

import java.io.Serializable;

public class PagingManagement implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167392837563298604L;
	private long totalRecord;
	private int currentPageNum = 1; // default is 1
	private int numberPerPage = 20; // default is 20 per page
	private int totalPageNum;
	private String action;
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		if (currentPageNum < 1)
		{
			this.currentPageNum = 1;
		}
		else
		{
			this.currentPageNum = currentPageNum;
		}
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) 
	{
		if (totalRecord < 0)
		{
			this.totalRecord = 0;
		}
		else
		{
			this.totalRecord = totalRecord;
		}
		
		this.calculateTotalPageNum();
	}
	
	public int getNumberPerPage() 
	{
		return numberPerPage;
	}
	public void setNumberPerPage(int numberPerPage) 
	{
		this.numberPerPage = numberPerPage;
		this.calculateTotalPageNum();
	}
	
	private void calculateTotalPageNum()
	{
		if (this.totalRecord > 0)
		{
			this.totalPageNum = ((Long) (this.totalRecord / this.numberPerPage)).intValue();
			if (this.totalRecord % this.numberPerPage > 0)
			{
				totalPageNum += 1;
			}
		}
		else
		{
			this.totalPageNum = 1;
		}
	}
	
	public int getFirstResult()
	{
		int firstResult = (this.currentPageNum - 1) * this.numberPerPage;
		return firstResult;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}
