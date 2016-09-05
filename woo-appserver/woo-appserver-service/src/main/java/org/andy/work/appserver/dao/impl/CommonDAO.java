package org.andy.work.appserver.dao.impl;

import java.util.Date;
import java.util.List;

import org.andy.work.appserver.dao.ICommonDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.IProvince;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.criteria.LogSearchCriteria;
import org.andy.work.paging.BasePaging;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.StringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class CommonDAO extends GenericDAO implements ICommonDAO {

	@Override
	public SearchResponse<ISystemLogs> searchLog(SearchRequest<LogSearchCriteria> searchRequest) {
		SearchResponse<ISystemLogs> searchResp = new SearchResponse<ISystemLogs>();
		LogSearchCriteria criteria = searchRequest.getCriteria();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = this.buildSystemLogsSearchCriteria(criteria, queryHelper);
		BasePaging paging = new BasePaging(searchRequest.getPgm(), whereHql.toString(), "SystemLogs", "ORDER BY w.createDate DESC", searchResp);
		this.searchPaging(paging, queryHelper);
		return searchResp;
	}
	
	private StringBuffer buildSystemLogsSearchCriteria(LogSearchCriteria criteria, QueryHelper queryHelper) {
		StringBuffer buff = new StringBuffer(" WHERE 1=1");
		if (StringUtil.hasValue(criteria.getKeyword())) {
			buff.append(" AND w.logsType like :keyword OR w.content like :keyword");
			buff.append(" OR w.createBy like :keyword OR w.addressIp like :keyword");
			queryHelper.addParameter("keyword", "%" + criteria.getKeyword() + "%");
		}
		if (StringUtil.hasValue(criteria.getOperationType())) {
			buff.append(" AND w.logsType = :logsType");
			queryHelper.addParameter("logsType", criteria.getOperationType());
		}
		
		if (StringUtil.hasValue(criteria.getDateStart())) {
			Date dateStart = CommonUtils.parseFromDate(criteria.getDateStart());
			if (dateStart != null) {
				buff.append(" AND w.createDate > :dateStart");
				queryHelper.addParameter("dateStart", dateStart);
			}
		}
		if (StringUtil.hasValue(criteria.getDateEnd())) {
			Date dateEnd = CommonUtils.parseToDate(criteria.getDateEnd());
			if (dateEnd != null) {
				buff.append(" AND w.createDate < :dateEnd");
				queryHelper.addParameter("dateEnd", dateEnd);
			}
		}
		return buff;
	}

	@Override
	public List<String> getLogsTypeList() {
		String queryString = "SELECT DISTINCT log.logsType FROM SystemLogs log";
		List<String> lists = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return lists;
	}

	@Override
	public List<IProvince> findProvinceByCountryId(Integer countryId) {
		String hql = "from Province p where p.country.id = :countryId order by p.id asc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("countryId", countryId);
		return query.list();
	}
	
	@Override
	public List<ICity> findCityByProvinceId(Integer provinceId) {
		String hql = "from City c where c.province.id = :provinceId order by c.id asc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("provinceId", provinceId);
		return query.list();
	}
	
	@Override
	public List<IArea> findAreaByCityId(Integer cityId) {
		String hql = "from Area a where a.city.id = :cityId order by a.id asc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("cityId", cityId);
		return query.list();
	}
}
