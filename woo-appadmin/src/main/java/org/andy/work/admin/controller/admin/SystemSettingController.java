package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.SystemHelper;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.ISystemSetting;
import org.andy.work.appserver.model.impl.SystemSetting;
import org.andy.work.appserver.service.ICommonMaintenanceService;
import org.andy.work.constant.SystemSettingKey;
import org.andy.work.type.LogsType;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统设置
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/portal/admin/system_setup")
public class SystemSettingController {
	
	@Resource
	private SystemHelper systemHelper;
	@Resource
	private ICommonMaintenanceService commonService;
	
	@RequestMapping
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_SETUP_VIEW)
	public ModelAndView index(ModelAndView model) {
		List<ISystemSetting> settings = this.systemHelper.getAllSystemSettings();
		
		Map<String, String> settingMap = new HashMap<String, String>();
		while (!settings.isEmpty()) {
			ISystemSetting s = settings.remove(0);
			settingMap.put(s.getKey(), s.getValue());
		}
		
		model.addAllObjects(settingMap)
			.setViewName("tiles/system-setting");
		
		return model;
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	@SystemLog(description="修改了系统设置", type=LogsType.INSERT)
	@AuthOperation(roleType=RoleType.ADMIN, operationType=AuthOperationConfiguration.SYSTEM_SETUP_SETTING)
	public AjaxResponse save(HttpServletRequest request) {
		List<ISystemSetting> systemSettings = new ArrayList<ISystemSetting>();
		List<String> keys = SystemSettingKey.getKeys();
		for (String key : keys) {
			String value = StringUtil.trim(request.getParameter(key));
			ISystemSetting  systemSetting = new SystemSetting();
			systemSetting.setKey(key);
			systemSetting.setValue(value);
			
			systemSettings.add(systemSetting);
			
			this.systemHelper.updateSystemSettings(systemSettings);
		}
		
		return AjaxResponse.success();
	}

	
}
