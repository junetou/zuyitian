package org.andy.work.admin.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.UserDetail;
import org.andy.work.admin.controller.admin.form.UserForm;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.log.LogsType;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.obj.MainMenuDisplay;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.INewtest;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.Newtest;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.INewtestMain;
import org.andy.work.appserver.service.ISolrPublishMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.RegularExpressUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 账号管理员
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/portal/admin/admin-account")
public class UserController {
	
	@Resource
	private UserHelper userHelper;
	
	@Resource
	private ISolrPublishMaintenanceService solrPublishService;
	
	
	@RequestMapping
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView search(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord) {
		GridData<UserDetail> grid = new GridData<UserDetail>();
		search.setKeyWork(keyWord);
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IUser> searchResp = this.userHelper.searchUser(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<UserDetail> displays = new ArrayList<UserDetail>();
			List<IUser> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IUser user = userGroups.get(i);
				UserDetail display = new UserDetail();
				display.setId(user.getId());
				display.setDname(user.getDisplayName());
				display.setDepart(user.getDepart());
				display.setGroup(user.getUserGroup().getName());
				display.setStaffno(user.getStaffNum());
				if(user.getLocked() != null && user.getLocked().equals("Y")) {
					display.setLocked(CommonUtils.getMessage("account.status.locked", request));
					display.setLock(false);
				} else {
					display.setLocked(CommonUtils.getMessage("account.status.active", request));
					display.setLock(true);
				} 
				display.setAccount(user.getUsername());
				display.setPwd(user.getPassword());
				display.setLast(CommonUtils.datetimeFormat(user.getLastLogin()));
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/usr-view");
		return model;
	}

	@Resource
	private INewtestMain iTestMain;
	
	
	@Resource
	private IDetailmessageMain iDetailMain; 
	
	@RequestMapping(value="/form/{userId}")
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView loadForm(@PathVariable Integer userId, ModelAndView model,HttpServletRequest request) {
		UserForm form = new UserForm();
		if (userId != null && userId != 0) {
			IUser user = this.userHelper.getUserById(userId);
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
			
			if (user.getUserGroup() != null) {
				form.setGroup(user.getUserGroup().getId());
			}
			
		}
		
	List<IUserGroup> groups = this.userHelper.getAllUserGroups();
	model.addObject("groups", groups);
	model.addObject("command", form);
	model.setViewName("tiles/includes/usr-form");
//	model.addObject("command", form);
		return model;
	}
	
	@RequestMapping(value="findAccount")
	@ResponseBody
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public Map<String, Boolean> findAccount(@RequestParam String account, @RequestParam(required=false, defaultValue="0") Integer id) {
		Map<String, Boolean> valid = new HashMap<String, Boolean>();
		if (id != null && id != 0) {
			IUser user = this.userHelper.getUserById(id);
			if (user.getUsername().equals(account)) {
				valid.put("valid", true);
			} else {
				boolean flag = !this.userHelper.isAccount(account);
				valid.put("valid", flag);
			}
		} else {
			boolean flag = !this.userHelper.isAccount(account);
			valid.put("valid", flag);
		}
		return valid;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(description="更新了管理员", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_EDIT)
	public AjaxResponse updateUser(UserForm userForm) {
		IUser user = this.getUser(userForm);
		try {
			this.userHelper.saveUser(user);
		} catch (Exception e) {
			return AjaxResponse.fail(0);
		}
		return AjaxResponse.success();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(description="创建了管理员", type=LogsType.SAVE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_ADD)
	public AjaxResponse addUser(UserForm userForm) {
		IUser user = this.getUser(userForm);
		try {
			this.userHelper.saveUser(user);
		} catch (Exception e) {
			return AjaxResponse.fail(0);
		}
		return AjaxResponse.success();
	}
	
	private IUser getUser(UserForm userForm) {
		IUser user;
		if(userForm.getId() != null) {
			user = this.userHelper.getUserById(userForm.getId());
			user.setDepart(userForm.getDepart());
			user.setDisplayName(userForm.getDname());
			user.setStaffNum(userForm.getStaffno());
			user.setUsername(userForm.getAccount());
			
			user.setMobile(userForm.getMobile());
			user.setPhone(userForm.getPhone());
			user.setFaxNum(userForm.getFaxNum());
			user.setEmail(userForm.getEmail());
			user.setAddress(userForm.getAddress());
			user.setWeixin(userForm.getWeixin());
			user.setQqNum(userForm.getQqNum());
			user.setRemark(userForm.getRemark());
			
			if (userForm.getGroup() != null) {
				IUserGroup group = this.userHelper.getUserGroup(userForm.getGroup());
				user.setUserGroup(group);
			}
		} else {
			user = this.userHelper.createUser();
			user.setDepart(userForm.getDepart());
			user.setDisplayName(userForm.getDname());
			user.setStaffNum(userForm.getStaffno());
			user.setUsername(userForm.getAccount());
			String encpwd = DigestUtils.md5Hex(userForm.getPwd());
			user.setPassword(encpwd);
			
			user.setMobile(userForm.getMobile());
			user.setPhone(userForm.getPhone());
			user.setFaxNum(userForm.getFaxNum());
			user.setEmail(userForm.getEmail());
			user.setAddress(userForm.getAddress());
			user.setWeixin(userForm.getWeixin());
			user.setQqNum(userForm.getQqNum());
			user.setRemark(userForm.getRemark());
			user.setLocked("");
			if (userForm.getGroup() != null) {
				IUserGroup group = this.userHelper.getUserGroup(userForm.getGroup());
				user.setUserGroup(group);
			}
		}
		return user;
	}
	
	//锁定
	@RequestMapping(value="lock")
	@ResponseBody
	@SystemLog(description="锁定了管理员", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_FREEZE)
	public AjaxResponse lockAccount(@RequestParam Integer id) {
		IUser user = this.userHelper.getUserById(id);
		if (user != null) {
			if (user.getLocked() != null && user.getLocked().equals("Y")) {
				return AjaxResponse.fail("锁定失败，'"+user.getUsername()+"'已被锁定");
			}else {
				user.setLocked("Y");
				try {
					this.userHelper.saveUser(user);
				} catch (Exception e) {
					return AjaxResponse.fail("锁定失败");
				}
			}
		} else {
			return AjaxResponse.fail("锁定失败，会员不存在！");
		}
		return AjaxResponse.success();
	}
		
	//解锁
	@RequestMapping(value="unlock")
	@ResponseBody
	@SystemLog(description="激活了管理员", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_FREEZE)
	public AjaxResponse unlockAccount(@RequestParam Integer id) {
		IUser user = this.userHelper.getUserById(id);
		if (user != null) {
			if (!(user.getLocked().equals("Y"))) {
				return AjaxResponse.fail("解锁失败，'"+user.getUsername()+"'未被锁定");
			}else {
				user.setLocked("");
				try {
					this.userHelper.saveUser(user);
				} catch (Exception e) {
					return AjaxResponse.fail("解锁失败");
				}
			}
		} else {
			return AjaxResponse.fail("解锁失败，会员不存在！");
		}
		return AjaxResponse.success();
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	@SystemLog(description="删除了管理员", type=LogsType.DELETE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_DELETE)
	public ModelAndView removeUser(@RequestParam Integer id,ModelAndView model) {
		// AjaxResponse
		//this.userHelper.removeUser(id);
		//return new AjaxResponse("1", "删除成功！");
		  model.setViewName("tiles/includes/test");
		  return model;
	}
	
	@RequestMapping(value="resetpass")
	@ResponseBody
	@SystemLog(description="更新密码了管理员", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_RESET)
	public AjaxResponse updatePassword(@RequestParam Integer userId, @RequestParam String password) {
		if (RegularExpressUtil.isCorrectPassword(password, 8, 16)) {
			IUser user = this.userHelper.getUserById(userId);
			String encpwd = DigestUtils.md5Hex(password);
			user.setPassword(encpwd);
			this.userHelper.saveUser(user);
			
			return AjaxResponse.success("密码重设成功！");
		}
		
		return AjaxResponse.fail("密码格式错误！请输入8~16位的数字和字母的字符组合");
	}
}
