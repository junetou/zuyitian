package org.andy.work.paging;

import java.io.Serializable;
import java.util.List;

/**
 *
 *developer
 *2015年2月3日下午9:48:15
 *
 */
public class GridData<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2035857166034579131L;
	private PagingManagement pgm;
	
	private boolean hasNext;
	private boolean hasPrev;
	private int prevPageNum;
	private int nextPageNum;
	private int totalPageNum;
	private long totalRecords;
	private List<Page> pages;
	
	private List<T> datas;
	
	
	public boolean isHasNext()
	{
		return hasNext;
	}
	public void setHasNext(boolean hasNext)
	{
		this.hasNext = hasNext;
	}
	public boolean isHasPrev()
	{
		return hasPrev;
	}
	public void setHasPrev(boolean hasPrev)
	{
		this.hasPrev = hasPrev;
	}
	public int getPrevPageNum()
	{
		return prevPageNum;
	}
	public void setPrevPageNum(int prevPageNum)
	{
		this.prevPageNum = prevPageNum;
	}
	public int getNextPageNum()
	{
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum)
	{
		this.nextPageNum = nextPageNum;
	}
	public int getTotalPageNum()
	{
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum)
	{
		this.totalPageNum = totalPageNum;
	}
	public List<Page> getPages()
	{
		return pages;
	}
	public void setPages(List<Page> pages)
	{
		this.pages = pages;
	}
	public List<T> getDatas()
	{
		return datas;
	}
	public void setDatas(List<T> datas)
	{
		this.datas = datas;
	}
	public long getTotalRecords()
	{
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public PagingManagement getPgm() {
		return pgm;
	}
	public void setPgm(PagingManagement pgm) {
		this.pgm = pgm;
	}
}
