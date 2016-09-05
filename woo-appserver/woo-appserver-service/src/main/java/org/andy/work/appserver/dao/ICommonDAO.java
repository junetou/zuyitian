package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.IProvince;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.criteria.LogSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ICommonDAO extends IGenericDAO {

	SearchResponse<ISystemLogs> searchLog(SearchRequest<LogSearchCriteria> searchRequest);

	List<String> getLogsTypeList();

	List<IProvince> findProvinceByCountryId(Integer countryId);

	List<ICity> findCityByProvinceId(Integer provinceId);

	List<IArea> findAreaByCityId(Integer cityId);

}
