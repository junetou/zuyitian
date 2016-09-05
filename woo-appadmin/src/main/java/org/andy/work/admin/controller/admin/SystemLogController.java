package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.LogDetail;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.appserver.service.ICommonMaintenanceService;
import org.andy.work.criteria.LogSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portal/system/log")
public class SystemLogController {
	
	@Resource ICommonMaintenanceService commonService;
	
	@RequestMapping
	@AuthOperation(roleType = RoleType.ADMIN, operationType = AuthOperationConfiguration.OPEATION_LOG_VIEW)
	public ModelAndView logList(ModelAndView model, HttpServletRequest request, LogSearchCriteria search) {
		
		GridData<LogDetail> grid = new GridData<LogDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		
		SearchResponse<ISystemLogs> searchResp = this.commonService.searchLog(new SearchRequest<LogSearchCriteria>(search, pgm));
		
		if (searchResp.getTotalRecords() > 0) {
			List<LogDetail> displays = new ArrayList<LogDetail>();
			List<ISystemLogs> logs = searchResp.getResults();
			for (int i = 0; i < logs.size(); i++) {
				ISystemLogs log = logs.get(i);
				LogDetail display = this.toLogDetail(log);
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		List<String> lists = this.commonService.getLogsTypeList();
		model.addObject("lists", lists).addObject("grid", grid).setViewName("loren/system/log_list");
		return model;
	}

	private LogDetail toLogDetail(ISystemLogs log) {
		LogDetail detail = new LogDetail();
		detail.setId(log.getId());
		detail.setLogsType(log.getLogsType());
		detail.setContent(log.getContent());
		detail.setCreateBy(log.getCreateBy());
		detail.setAddressIp(log.getAddressIp());
		detail.setCreateDate(CommonUtils.datetimeFormat(log.getCreateDate()));
		return detail;
	}
}
