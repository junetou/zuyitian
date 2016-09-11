package org.andy.work.admin.permission;

import java.util.ArrayList;
import java.util.List;

public enum Permission {
	
	//系统管理
	ADMIN_ACCOUNT("ADMIN_ACCOUNT", "admin/admin-account", MenuType.ADMIN_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	SYSTEM_ROLE("SYSTEM_ROLE", "admin/system-role", MenuType.ADMIN_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE}),
	FED("FED","admin/fed/adminwatchfed", MenuType.ADMIN_MENU,"",new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE}),
	
	//内容管理
	ARTICLE("ARTICLE", "web/article", MenuType.WEB_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.EXAMINE, OperationType.RELEASE, OperationType.EXPORT}),
	ARTICLE_CATEGORY("ARTICLE_CATEGORY", "web/article/category", MenuType.WEB_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE}),
	
	//物品操作
	MAP("MAP", "admin/map/showmap",MenuType.COMMENT_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	THINGS("THINGS","admin/admin-message/showmessage",MenuType.COMMENT_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	ADDMESSAGE("ADDMESSAGE","admin/addmessage/show",MenuType.COMMENT_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	COMMENT("COMMENT","admin/comment/showcomment",MenuType.COMMENT_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),	
	//需求操作
	NEEDMAP("NEEDMAP","admin/need/showmap",MenuType.NEEDCONTROL_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	CUSTOMER("CUSTOMER","admin/need/show",MenuType.NEEDCONTROL_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	NEED("NEED","admin/need/addneed",MenuType.NEEDCONTROL_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),	
	MENEED("MENEED","admin/need/showmyneed",MenuType.NEEDCONTROL_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	//个人操作
	SELLER("SELLER","admin/trade/seller",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	BORROW("BORROW","admin/trade/borrow",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	TRADE("TRADE","admin/trade/tradeing",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	MY("MY","admin/updatemessage/look",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	CHAT("CHAT","admin/chat/wchat",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	LEGAL("LEGAL","admin/legal/showlegal",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW,OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE});
	
	private String code;
	private String url;
	private int type;
	private String classIcon;
	private Integer[] projects;
	private String[] opts;
	
	private Permission(String code, String url, int type, String classIcon, String[] opts) {
		this.code = code;
		this.url = url;
		this.type = type;
		this.opts = opts;
		this.classIcon = classIcon;
	}
	
	private Permission(String code, String url, int type, String classIcon, Integer[] projects, String[] opts) {
		this.code = code;
		this.url = url;
		this.type = type;
		this.projects = projects;
		this.opts = opts;
		this.classIcon = classIcon;
	}
	
	public static List<Permission> getMenus(int menuType) {
		return getMenus(menuType, null);
	}
	
	public static List<Permission> getMenus(int menuType, Integer projectType) {
		List<Permission> permissions = new ArrayList<Permission>();
		for (Permission p : Permission.values()) {
			if (p.type == menuType && p.containsProjectType(projectType)) {
				permissions.add(p);
			}
		}
		
		return permissions;
	}
	
	private boolean containsProjectType(Integer projectType) {
		if (this.projects == null) {
			return true;
		}
		
		if (projectType == null) {
			return false;
		}
		
		for (Integer type: this.projects) {
			if (type.intValue() == projectType.intValue()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Permission getByCode(String code) {
		for (Permission p : Permission.values()) {
			if (p.getCode().equals(code)) {
				return p;
			}
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getUrl() {
		return url;
	}

	public int getType() {
		return type;
	}

	public String[] getOpts() {
		return opts;
	}

	public Integer[] getProjects() {
		return projects;
	}

	public String getClassIcon() {
		return classIcon;
	}

	public void setClassIcon(String classIcon) {
		this.classIcon = classIcon;
	}
}
