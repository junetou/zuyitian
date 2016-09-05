package org.andy.work.paging;

import java.io.Serializable;
import java.util.List;

public class SearchResponse<T> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8357359326395593921L;
	private long totalRecords;
	private List<T> results;
	
	public long getTotalRecords()
	{
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	public List<T> getResults()
	{
		return results;
	}
	public void setResults(List<T> results)
	{
		this.results = results;
	}
	@SuppressWarnings("unchecked")
	public void setResultsObj(List<Object> objs) {
		this.results = (List<T>) objs;
	}
}
