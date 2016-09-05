package org.andy.work.admin.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.UserDetail;
import org.andy.work.admin.controller.admin.form.UserForm;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.log.LogsType;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.RegularExpressUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/portal/admin/updatemessage")
public class UpdateUserController {
	
	@Resource
	private UserHelper userhelper;
	
	@RequestMapping(value="/look")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView showmessage(ModelAndView model,HttpServletRequest request){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user = this.userhelper.findUserByUsername(userDetails.getUsername());
        request.setAttribute("name", user.getDisplayName());
		request.setAttribute("usename", user.getUsername());
	    model.setViewName("tiles/showmessage");
		return model;
	}
	
	
	
	
	
	@RequestMapping(value="/show")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView updateusemessage(ModelAndView model){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name=userDetails.getUsername();
		UserForm form = new UserForm();
		IUser user = this.userhelper.findUserByUsername(name);
		
		form.setAccount(user.getUsername());
		form.setDepart(user.getDepart());
		form.setDname(user.getDisplayName());
		form.setId(user.getId());
		form.setPwd("***");
		form.setStaffno(user.getStaffNum());
		
		form.setMobile(user.getMobile());
		form.setPhone(user.getPhone());
		form.setFaxNum(user.getFaxNum());
		form.setEmail(user.getEmail());
		form.setAddress(user.getAddress());
		form.setWeixin(user.getWeixin());
		form.setQqNum(user.getQqNum());
		form.setRemark(user.getRemark());
		model.addObject("command", form);
		model.setViewName("tiles/includes/updateuser");	
		return  model;
	}
	
	

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	private AjaxResponse update(UserForm userForm,ModelAndView model,HttpServletRequest request){
		IUser user = this.getUser(userForm);
		try {
			this.userhelper.saveUser(user);
		} catch (Exception e) {
			return AjaxResponse.fail(0);
		}
		return AjaxResponse.success();
	}
	
	
	private IUser getUser(UserForm userForm) {
		    AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    IUser noupdateuser=this.userhelper.findUserByUsername(userDetails.getUsername());
		    IUser user;
		    user = this.userhelper.getUserById(userForm.getId());
			user.setDepart(userForm.getDepart());
			user.setDisplayName(userForm.getDname());
			user.setStaffNum(noupdateuser.getStaffNum());
			user.setUsername(noupdateuser.getUsername());
			
			user.setMobile(userForm.getMobile());
			user.setPhone(userForm.getPhone());
			user.setFaxNum(userForm.getFaxNum());
			user.setEmail(userForm.getEmail());
			user.setAddress(userForm.getAddress());
			user.setWeixin(userForm.getWeixin());
			user.setQqNum(userForm.getQqNum());
			user.setRemark(userForm.getRemark());
			user.setUserGroup(noupdateuser.getUserGroup());
		    user.setPassword(noupdateuser.getPassword());
		return user;
	}
	
	
	@RequestMapping(value="/resetpass")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.MAP_VIEW)
	public AjaxResponse updatePassword(HttpServletRequest request) {
		  AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String password=request.getParameter("password");
		  if (this.userhelper.hasusrname(userDetails.getName())) {
			IUser user = this.userhelper.findUserByUsername(userDetails.getUsername());
			String encpwd = DigestUtils.md5Hex(password);
			user.setPassword(encpwd);
			this.userhelper.saveUser(user);		
			return AjaxResponse.success("密码重设成功！");
		}
		
		return AjaxResponse.fail("密码格式错误！请输入8~16位的数字和字母的字符组合");
	}

}
