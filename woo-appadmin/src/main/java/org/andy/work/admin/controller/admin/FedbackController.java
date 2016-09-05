package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.IFedMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portal/admin/fed")
public class FedbackController {
  
	@RequestMapping(value="/showfed")
	@ResponseBody 
	public ModelAndView showfed(ModelAndView model){
	    model.setViewName("tiles/includes/fedback");
		return model;
	}
	
	@Resource
	IDetailmessageMain message;
	
	@Resource
    UserHelper userHelper;
	
	@Resource
	IFedMain fedmain;
	
	
	@RequestMapping(value="/fed")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView fed(ModelAndView model,HttpServletRequest request){
		String name=request.getParameter("thingsname");
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		String usernames=userDetails.getName();
		IUser use=userHelper.findUserByUsername(usename);		
		Fed fed=new Fed();
		fed.setDesc(name);
		fed.setThingid(use.getId());
		fed.setName(usernames);
		String judgement=fedmain.addfed(fed);
		if(judgement.equals("success"))
		{
		request.setAttribute("message", "反馈成功");
		}
		model.setViewName("tiles/includes/fedsuccess");
		return model;
	}
	
	@RequestMapping(value="/adminwatchfed")
	@ResponseBody
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView adminwatchfed(ModelAndView model,HttpServletRequest request,AcctUserSearchCriteria search){
		
		GridData<Fed> grid = new GridData<Fed>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IFed> searchResp = this.fedmain.searchUsers(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<Fed> displays = new ArrayList<Fed>();
			List<IFed> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IFed user = userGroups.get(i);
				Fed display = new Fed();
				display.setFedid(user.getFedid());
				display.setDesc(user.getDesc());
				display.setThingid(user.getThingid());
				display.setName(user.getName());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/usr-fedbanck");
		return model;
	}
	
	
	
}
