package org.andy.work.paging;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *developer
 *2015年2月3日下午9:57:32
 *
 */
public class PagingHelper {
	
	public final static int MAX_PAGE_NUM = 8;
	
	public static final int DEFAULT_CURRENT_PAGE_NUM = 1;
	public static int DEFALUT_NUMBER_PER_PAGE = 15;
	
	public static void setNumberPage(int pageSize) {
		DEFALUT_NUMBER_PER_PAGE = pageSize;
	}
	
	public static PagingManagement buildPagingManagement(HttpServletRequest request)
	{	
		int currentPageNum;
		try {
			currentPageNum = Math.abs(Integer.parseInt(request.getParameter("pn")));
			if (currentPageNum == 0) {
				currentPageNum = DEFAULT_CURRENT_PAGE_NUM;
			}
		} catch (Exception e) {
			currentPageNum = DEFAULT_CURRENT_PAGE_NUM;
		}
		
		PagingManagement pgm = new PagingManagement();
		pgm.setCurrentPageNum(currentPageNum);
		pgm.setNumberPerPage(DEFALUT_NUMBER_PER_PAGE);
		
		return pgm;
	}
	
	public static void setPaging(PagingManagement pgm, GridData<?> gridData) {
		gridData.setPgm(pgm);
		
		int startPageNum = pgm.getCurrentPageNum() - 2;
		if (startPageNum <= 2)
		{
			startPageNum = 1;
		}
		
		if (startPageNum > 1)
		{
			gridData.setHasPrev(true);
		}
		
		//set prev page num
		int prevPageNum = pgm.getCurrentPageNum() - 1;
		if (prevPageNum < 1)
		{
			prevPageNum = 1;
		}
		gridData.setPrevPageNum(prevPageNum);
		
		
		int endPageNum = startPageNum + MAX_PAGE_NUM; 
		if (endPageNum > pgm.getTotalPageNum() - 1)
		{
			endPageNum = pgm.getTotalPageNum();
		}
		
		if (endPageNum < pgm.getTotalPageNum())
		{
			gridData.setHasNext(true);
			gridData.setTotalPageNum(pgm.getTotalPageNum());
		}
		
		// set next page number
		int nextPageNum = pgm.getCurrentPageNum() + 1;
		if (nextPageNum > pgm.getTotalPageNum())
		{
			nextPageNum = pgm.getTotalPageNum();
		}
		gridData.setNextPageNum(nextPageNum);
		
		//set totalrecords
		gridData.setTotalRecords(pgm.getTotalRecord());
		
		//set pages
		List<Page> pages = new ArrayList<Page>();
		for (; startPageNum <= endPageNum; startPageNum++)
		{
			Page page = new Page();
			page.setNum(startPageNum + "");
			
			if (startPageNum == pgm.getCurrentPageNum())
			{
				page.setActive(true);
			}
			
			pages.add(page);
		}
		
		gridData.setPages(pages);
	}
}
