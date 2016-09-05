package org.andy.work.criteria;

import java.io.Serializable;

public class LogSearchCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String keyword;
	
	private String operationType;
	
	private String dateStart;
	
	private String dateEnd;
	

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
}
