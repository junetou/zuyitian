package org.andy.work.appserver.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IArticleDAO;
import org.andy.work.appserver.dao.ICommonDAO;
import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.ICountry;
import org.andy.work.appserver.model.IProvince;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.appserver.model.impl.Country;
import org.andy.work.appserver.service.ICommonMaintenanceService;
import org.andy.work.appserver.service.ISolrSearchMaintenanceService;
import org.andy.work.criteria.LogSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public class CommonMaintenanceService implements ICommonMaintenanceService {
	
	@Resource 
	private ICommonDAO commonDAO;
	@Resource
	private IArticleDAO articleDAO;
	@Resource
	private ISolrSearchMaintenanceService solrSearch;

	@Override
	public Object makePersist(Object obj) {
		return this.commonDAO.makePersist(obj);
	}
	@Override
	public void makeUpdate(Object obj) {
		this.commonDAO.makeUpdate(obj);
	}
	@Override
	public void makeSaveOrUpdate(Object obj) {
		this.commonDAO.makeSaveOrUpdate(obj);
	}
	@Override
	public Object getEntityById(Class<?> clazz, Serializable id) {
		return this.commonDAO.getEntityById(clazz, id);
	}
	@Override
	public void deleteEntity(Object obj) {
		this.commonDAO.deleteEntity(obj);
	}

	@Override
	public List<String> getLogsTypeList() {
		return this.commonDAO.getLogsTypeList();
	}


	@Override
	public ICountry getCountryById(Integer countryId) {
		return (ICountry) this.commonDAO.getEntityById(Country.class, countryId);
	}
	
	@Override
	public List<IProvince> findProvinceByCountryId(Integer countryId) {
		return this.commonDAO.findProvinceByCountryId(countryId);
	}
	
	@Override
	public List<ICity> findCityByProvinceId(Integer provinceId) {
		return this.commonDAO.findCityByProvinceId(provinceId);
	}
	
	@Override
	public List<IArea> findAreaByCityId(Integer cityId) {
		return this.commonDAO.findAreaByCityId(cityId);
	}
	@Override
	public SearchResponse<ISystemLogs> searchLog(SearchRequest<LogSearchCriteria> searchRequest) {
		return this.commonDAO.searchLog(searchRequest);
	}
}
