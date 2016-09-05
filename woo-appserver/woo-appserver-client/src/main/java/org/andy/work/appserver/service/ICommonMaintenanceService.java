package org.andy.work.appserver.service;

import java.io.Serializable;
import java.util.List;

import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.ICountry;
import org.andy.work.appserver.model.IProvince;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.criteria.LogSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ICommonMaintenanceService {

	Object makePersist(Object obj);
	
	void makeUpdate(Object obj);
	
	void makeSaveOrUpdate(Object obj);
	
	Object getEntityById(Class<?> clazz, Serializable id);
	
	void deleteEntity(Object obj);
	
	SearchResponse<ISystemLogs> searchLog(SearchRequest<LogSearchCriteria> searchRequest);

	List<String> getLogsTypeList();

	ICountry getCountryById(Integer countryId);

	List<IProvince> findProvinceByCountryId(Integer countryId);

	List<ICity> findCityByProvinceId(Integer id);

	List<IArea> findAreaByCityId(Integer id);

}
