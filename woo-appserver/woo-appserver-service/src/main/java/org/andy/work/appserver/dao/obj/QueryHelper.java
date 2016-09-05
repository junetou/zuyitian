package org.andy.work.appserver.dao.obj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.andy.work.paging.PagingManagement;

public class QueryHelper {
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public void addParameter(String key, Object value) {
		this.map.put(key, value);
	}
	
	@SuppressWarnings("rawtypes")
	public void setToQuery(Query query, PagingManagement pgm) {
		if (!this.map.isEmpty()) {
			for (Entry<String, Object> entry : this.map.entrySet()) {
				if (entry.getValue() instanceof List) {
					query.setParameterList(entry.getKey(), (List) entry.getValue());
				} else if (entry.getValue() instanceof Date) {
					query.setTimestamp(entry.getKey(), (Date) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		
		if (pgm != null) {
			query.setFirstResult(pgm.getFirstResult()).setMaxResults(pgm.getNumberPerPage());
		}
		query.setCacheable(true).list();
	}
	
	@SuppressWarnings("rawtypes")
	public void setQuery(Query query, PagingManagement pgm) {
		if (!this.map.isEmpty()) {
			for (Entry<String, Object> entry : this.map.entrySet()) {
				if (entry.getValue() instanceof List) {
					query.setParameterList(entry.getKey(), (List) entry.getValue());
				} else if (entry.getValue() instanceof Date) {
					query.setTimestamp(entry.getKey(), (Date) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		
		if (pgm != null) {
			query.setFirstResult(pgm.getFirstResult()).setMaxResults(pgm.getNumberPerPage());
		}
	}
	
	private QueryHelper() { }
	
	public static QueryHelper getInstance() {
		return new QueryHelper();
	}
}
