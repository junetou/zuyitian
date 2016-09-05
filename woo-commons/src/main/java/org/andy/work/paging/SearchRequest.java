package org.andy.work.paging;

import java.io.Serializable;

public class SearchRequest<T> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2290311149962496804L;
	private PagingManagement pgm;
	private T criteria;
	
	public SearchRequest(T criteria, PagingManagement pgm)
	{
		this.criteria = criteria;
		this.pgm = pgm;
	}
	
	public PagingManagement getPgm()
	{
		return pgm;
	}
	public void setPgm(PagingManagement pgm)
	{
		this.pgm = pgm;
	}
	public T getCriteria()
	{
		return criteria;
	}
	public void setCriteria(T criteria)
	{
		this.criteria = criteria;
	}
}
