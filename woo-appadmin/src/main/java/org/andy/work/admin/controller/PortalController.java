package org.andy.work.admin.controller;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.helper.SystemHelper;
import org.andy.work.admin.obj.MainMenuDisplay;
import org.andy.work.admin.obj.MenuNode;
import org.andy.work.admin.permission.MenuType;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.Permission;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.utils.CommonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortalController {
	
	@Resource
	private SystemHelper systemHelper;
	
	@RequestMapping(value="/portal", method=RequestMethod.GET)
	public ModelAndView portal(ModelAndView model, HttpServletRequest request) {
		
		//安全访问(访问) 获得当前已认证的用户信息
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<MainMenuDisplay> menus = new ArrayList<MainMenuDisplay>();
		
		//系统管理
		if (this.hasRole(userDetails, RoleType.ADMIN)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.admin", request));
			menu.setType(MenuType.ADMIN_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.ADMIN_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-cog");
			menus.add(menu);
		}
		
		//用户管理
		if (this.hasRole(userDetails, RoleType.ACCOUNT)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.user", request));
			menu.setType(MenuType.ACCOUNT_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.ACCOUNT_MENU.intValue(), model, request));
			menu.setClassIcon("fa fa-user");
			menus.add(menu);
		}
		
		//内容管理
		if (this.hasRole(userDetails, RoleType.WEB)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.web", request));
			menu.setType(MenuType.WEB_MENU.intValue());
			LinkedList<MenuNode> nodes = this.loadSubMenus(MenuType.WEB_MENU.intValue(), model, request);
			menu.setNodes(nodes);
			menu.setClassIcon("fa fa-leaf");
			menus.add(menu);
		}
		
		//商品管理
		if (this.hasRole(userDetails, RoleType.PRODUCT_CENTRAL)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.product", request));
			menu.setType(MenuType.PROD_CENTRAL_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.PROD_CENTRAL_MENU.intValue(), model, request));
			menu.setClassIcon("fa fa-comment");
			menus.add(menu);
		}
		
		//订单管理
		if (this.hasRole(userDetails, RoleType.ORDER_CENTRAL)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.order_central", request));
			menu.setType(MenuType.ORDER_CENTRAL_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.ORDER_CENTRAL_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-lock");
			menus.add(menu);
		}

		//营销管理
		if (this.hasRole(userDetails, RoleType.MARK_CENTRAL)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.marketing", request));
			menu.setType(MenuType.MARK_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.MARK_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-list-alt");
			menus.add(menu);
		}
		
		//财务管理
		if (this.hasRole(userDetails, RoleType.PROPERTY)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.property", request));
			menu.setType(MenuType.PROPERTY_MENU.intValue());
			LinkedList<MenuNode> nodes = this.loadSubMenus(MenuType.PROPERTY_MENU.intValue(), model, request);
			menu.setNodes(nodes);
			menu.setClassIcon("glyphicon glyphicon-yen");
			menus.add(menu);
		}
		
		//地图
		if(this.hasRole(userDetails, RoleType.MAP)){
			MainMenuDisplay menu =new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.map", request));
			menu.setType(MenuType.MAP_MENU.intValue());
			LinkedList<MenuNode> node=this.loadSubMenus(MenuType.MAP_MENU.intValue(),model, request);
			menu.setNodes(node);
			menu.setClassIcon("fa fa-comment");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.COMMENT)){
			MainMenuDisplay menu=new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.me",request));
			menu.setNodes(this.loadSubMenus(MenuType.COMMENT_MENU.intValue(), model, request));
			menu.setType(MenuType.COMMENT_MENU.intValue());
			menu.setClassIcon("glyphicon glyphicon-gift");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.NEEDCONTROL)){
			MainMenuDisplay menu=new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.needcontroller",request));
			menu.setType(MenuType.NEEDCONTROL_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.NEEDCONTROL_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-list-alt");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.TRADE)){
			MainMenuDisplay menu=new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.tradecontroller",request));
			menu.setType(MenuType.TRADE_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.TRADE_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-usd");
			menus.add(menu);
		}
		
		model.addObject("menus", menus).setViewName("tiles/portalview");
		
		return model;
	}
	
	private boolean hasRole(AdminUserDetails userDetails, String role) {
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			if (g.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<MenuNode> loadSubMenus(Integer menuType, ModelAndView model, HttpServletRequest request) {
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();
		
		switch (menuType) {
			case 10:
				if (this.hasNode(userDetails, RoleType.PROPERTY)) {
					menus.addAll(this.getCommonMenus(request, userDetails));
				}
				break;
			case 20:
				if (this.hasNode(userDetails, RoleType.ADMIN)) {
					menus.addAll(this.getAdminMenus(request, userDetails));
				}
				break;
			case 30:
				if (this.hasNode(userDetails, RoleType.WEB)) {
					menus.addAll(this.getWebMenus(request, userDetails));
				}
				break;
			case 40:
				if (this.hasNode(userDetails, RoleType.ORDER_CENTRAL)) {
					menus.addAll(this.getOrderCentralMunus(request, userDetails));
				}
				break;
			case 50:
				if (this.hasNode(userDetails, RoleType.PRODUCT_CENTRAL)) {
					menus.addAll(this.getProductCentralMunus(request, userDetails));
				}
				break;
			case 60:
				if (this.hasNode(userDetails, RoleType.MARK_CENTRAL)) {
					menus.addAll(this.getWarehouseCentralMunus(request, userDetails));
				}
				break;
			case 70:
				if (this.hasNode(userDetails, RoleType.USER)) {
					menus.addAll(this.getUserCentralMunus(request, userDetails));
					break;
				}
			case 80:
				if(this.hasNode(userDetails, RoleType.MAP)){
		            menus.addAll(this.getMap(request, userDetails));
		            break;
				}
			case 90:
				if(this.hasNode(userDetails, RoleType.COMMENT)){
					menus.addAll(this.getCommentMunus(request, userDetails));
					break;
				}
			case 100:
				if(this.hasNode(userDetails, RoleType.NEEDCONTROL)){
					menus.addAll(this.getNeedControllerMunus(request, userDetails));
					break;
				}
			case 110:
				if(this.hasNode(userDetails, RoleType.TRADE)){
					menus.addAll(this.getTradeMunus(request, userDetails));
					break;
				}
 		}
		
		return menus;
	}

	private boolean hasNode(AdminUserDetails userDetails, String role) {
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			if (g.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	private String getUrl(String code, HttpServletRequest request) {
		Permission permission = Permission.getByCode(code);
		if (permission != null) {
			return "portal/"+permission.getUrl();
		}
		return null;
	}
	
	private String getClassIcon(String code) {
		Permission permission = Permission.getByCode(code);
		if (permission != null) {
			return permission.getClassIcon();
		}
		return null;
	}
	
	private LinkedList<MenuNode> getUserCentralMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();
		
		List<Permission> permissions = Permission.getMenus(MenuType.ACCOUNT_MENU);
		for (Permission perm : permissions) {
			if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
				String url = this.getUrl(perm.getCode(), request);
				menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
						, 1, MenuType.ACCOUNT_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getCommonMenus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();
		
		List<Permission> permissions = Permission.getMenus(MenuType.PROPERTY_MENU);
		for (Permission perm : permissions) {
			if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
				String url = this.getUrl(perm.getCode(), request);
				menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
						, 1, MenuType.PROPERTY_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getAdminMenus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.ADMIN_MENU);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.ADMIN_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getWebMenus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		Integer projectType = null;
		List<Permission> permissions = Permission.getMenus(MenuType.WEB_MENU, projectType);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.WEB_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getOrderCentralMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.ORDER_CENTRAL_MENU);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.ORDER_CENTRAL_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getProductCentralMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.PROD_CENTRAL_MENU);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.PROD_CENTRAL_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getWarehouseCentralMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.MARK_MENU);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.MARK_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getMap(HttpServletRequest request, AdminUserDetails userDetails){
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();
		List<Permission> permissions=Permission.getMenus(MenuType.MAP_MENU);
		if(!permissions.isEmpty())
		{
			for(Permission perm : permissions)
			{
				if(userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)){
					String url=this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.MAP_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getCommentMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.COMMENT_MENU);
		
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.COMMENT_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		
		return menus;
	}
	
        private LinkedList<MenuNode> getNeedControllerMunus(HttpServletRequest request, AdminUserDetails userDetails) {
		
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.NEEDCONTROL_MENU);
	
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.NEEDCONTROL_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
	
		return menus;
	}
	
        private LinkedList<MenuNode> getTradeMunus(HttpServletRequest request, AdminUserDetails userDetails) {
    		
    		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

    		List<Permission> permissions = Permission.getMenus(MenuType.TRADE_MENU);
    		
    			for (Permission perm : permissions) {
    				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
    					String url = this.getUrl(perm.getCode(), request);
    					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
    							, 1, MenuType.TRADE_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
    				}
    			}
    		
    		return menus;
    	}

}
