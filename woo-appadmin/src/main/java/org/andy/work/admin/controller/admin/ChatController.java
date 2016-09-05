package org.andy.work.admin.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin/chat")
public class ChatController {
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/wchat")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView chat(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
		
	    request.setAttribute("usename",use.getUsername());
		request.setAttribute("password", "123456");
	    
		model.setViewName("tiles/chat");
		return model;
	} 
	
	@Resource
	private UserHelper userhelper;
	
	@RequestMapping(value="/get")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView thingschat(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	    request.setAttribute("usename",use.getUsername());
		request.setAttribute("password", "123456");
	    String thingnumber=request.getParameter("usrid");
	    IUser user=this.userhelper.findUserByUsername(thingnumber);
	    String name=user.getUsername();
        request.setAttribute("thingnumber", name);
		model.setViewName("tiles/includes/wchat");
		return model;
	} 
	

}
