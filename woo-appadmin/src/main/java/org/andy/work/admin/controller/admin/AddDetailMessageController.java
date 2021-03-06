package org.andy.work.admin.controller.admin;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin/addmessage")
public class AddDetailMessageController {

	
	@RequestMapping(value="/show")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView showmessage(ModelAndView model){
		model.setViewName("tiles/includes/addmap");		
		return model;
	} 

}
