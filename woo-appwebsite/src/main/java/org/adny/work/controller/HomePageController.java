package org.adny.work.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.appserver.service.ISolrPublishMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
	
	@Resource
	private ISolrPublishMaintenanceService solrPublishService;
	
	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView browseHome(HttpServletRequest request, ModelAndView model) {
		model.addObject("title", CommonUtils.getMessage("dysc.home.title", request));
		model.setViewName("home-page/index");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search(AcctUserSearchCriteria criteria, HttpServletRequest request, ModelAndView model) {
		GridData<AcctUserSearchCriteria> grid = new GridData<AcctUserSearchCriteria>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		
		SearchResponse<AcctUserSearchCriteria> searchResp = this.solrPublishService.keywordSearch(new SearchRequest<AcctUserSearchCriteria>(criteria, pgm));
		
		if (searchResp.getTotalRecords() > 0) {
			grid.setDatas(searchResp.getResults());
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("home-page/search-view");
		return model;
	}

}
