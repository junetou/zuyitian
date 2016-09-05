package org.andy.work.paging;

public class BasePaging {
	
	private PagingManagement pgm;
	private String whereHql;
	private String className;
	private String sortMethod;
	private SearchResponse<?> searchResponse;
	
	public BasePaging(PagingManagement pgm, String whereHql, String className,
			String sortMethod, SearchResponse<?> searchResponse) {
		super();
		this.pgm = pgm;
		this.whereHql = whereHql;
		this.className = className;
		this.sortMethod = sortMethod;
		this.searchResponse = searchResponse;
	}
	public BasePaging() {
		super();
	}
	
	public PagingManagement getPgm() {
		return pgm;
	}
	public void setPgm(PagingManagement pgm) {
		this.pgm = pgm;
	}
	public String getWhereHql() {
		return whereHql;
	}
	public void setWhereHql(String whereHql) {
		this.whereHql = whereHql;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSortMethod() {
		return sortMethod;
	}
	public void setSortMethod(String sortMethod) {
		this.sortMethod = sortMethod;
	}
	public SearchResponse<?> getSearchResponse() {
		return searchResponse;
	}
	public void setSearchResponse(SearchResponse<?> searchResponse) {
		this.searchResponse = searchResponse;
	}
	
}
