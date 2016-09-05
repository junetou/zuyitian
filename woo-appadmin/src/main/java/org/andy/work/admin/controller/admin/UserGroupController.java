package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.UserGroupDetail;
import org.andy.work.admin.controller.admin.form.UserGroupForm;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.log.LogsType;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.MenuType;
import org.andy.work.admin.permission.Permission;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/portal/admin/system-role")
public class UserGroupController {
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_ROLE_VIEW)
	public ModelAndView search(ModelAndView model, HttpServletRequest request, @RequestParam(required=false) String keyword) {
		GridData<UserGroupDetail> grid = new GridData<UserGroupDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		
		SearchResponse<IUserGroup> searchResp = this.userHelper.searchSearchResponse(new SearchRequest<String>(keyword, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<UserGroupDetail> displays = new ArrayList<UserGroupDetail>();
			List<IUserGroup> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IUserGroup userGroup = userGroups.get(i);
				List<String> users = this.userHelper.getGroupUsers(userGroup.getId());
				UserGroupDetail display = this.toUserGroupDisplay(userGroup);
				display.setOpts(null);
				display.setUsrs(users.toString());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/group-view");
		return model;
	}
	
	@RequestMapping(value="/form/{groupId}")
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_ROLE_VIEW)
	private ModelAndView loadForm(@PathVariable Integer groupId, ModelAndView model) {
		UserGroupForm form;
		if (groupId != null && groupId != 0) {
			IUserGroup userGroup = this.userHelper.getUserGroup(groupId);
			form = this.toGroupForm(userGroup);
		} else {
			form = new UserGroupForm();
		}
		
		model.addObject("adminMenus", Permission.getMenus(MenuType.ADMIN_MENU))
			.addObject("webMenus", Permission.getMenus(MenuType.WEB_MENU))
			.addObject("mapMenus",Permission.getMenus(MenuType.MAP_MENU))
			.addObject("commentMenus",Permission.getMenus(MenuType.COMMENT_MENU))
			.addObject("command", form)
			.setViewName("tiles/includes/group-form");
		return model;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(description="更新了权限组", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_ROLE_EDIT)
	private AjaxResponse updateGroup(UserGroupForm form) {
		IUserGroup group = this.userHelper.getUserGroup(form.getId());
		group.setName(form.getGname());
		group.setPermission(this.getPermission(form.getOpts()));
//		group.setRole(this.getRoles(form.getRoles()));
		this.userHelper.saveUserGroup(group);
		
		return AjaxResponse.success();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(description="创建了权限组", type=LogsType.SAVE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_ROLE_ADD)
	private AjaxResponse addGroup(UserGroupForm form) {
		if (this.userHelper.isUserGroupExists(form.getGname())) {
			return new AjaxResponse(null, "组名称已经存在");
		}
		
		IUserGroup group = this.userHelper.createUserGroup();
		group.setName(form.getGname());
		group.setPermission(this.getPermission(form.getOpts()));
		group.setRole(this.getRoles(form.getRoles()));
		
		this.userHelper.saveUserGroup(group);
		
		return new AjaxResponse("1", "新建用户组成功！");
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	@SystemLog(description="删除了权限组", type=LogsType.DELETE)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_ROLE_DELETE)
	private AjaxResponse removeGroup(@RequestParam Integer id) {
		if (this.userHelper.isUserGroup(id)) {
			return AjaxResponse.fail("此用户组不能删除！");
		}
		this.userHelper.removeUserGroup(id);
		return new AjaxResponse("1", "删除成功！");
	}
	
	private String getPermission(String[] opts) {
		StringBuffer sb = new StringBuffer();
		if (opts != null) {
			for (int i = 0; i < opts.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append(opts[i]);
			}
		}
		
		return sb.toString();
	}
	
	private String getRoles(String[] roles) {
		StringBuilder sb = new StringBuilder();
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("ROLE_" + roles[i].toUpperCase());
			}
		}
		return sb.toString();
	}
	
	private UserGroupDetail toUserGroupDisplay(IUserGroup userGroup) {
		UserGroupDetail display = new UserGroupDetail();
		display.setGname(userGroup.getName());
		display.setId(userGroup.getId());
		if (StringUtil.hasValue(userGroup.getPermission())) {
			display.setOpts(userGroup.getPermission().split(","));
		}
		return display;
	}
	
	private UserGroupForm toGroupForm(IUserGroup group) {
		UserGroupForm form = null;
		if (group != null) {
			form = new UserGroupForm();
			form.setId(group.getId());
			form.setGname(group.getName());
			if (StringUtil.hasValue(group.getPermission())) {
				form.setOpts(group.getPermission().split(","));
			}
		}
		return form;
	}
}
