package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin/map")
public class Map {

	@Resource
	ICommentMain commentmain;
	
	@Resource
	private IDetailmessageMain iDetailMain; 
	
	@RequestMapping(value="/showmap")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView chat(ModelAndView model){
		
		 
        int count=this.iDetailMain.checkcount();
        Integer newcount=Integer.valueOf(count);
        Integer con=1;
        List<IDetailmessage> usemessage=new ArrayList<IDetailmessage>();
        for(con=1;con<=newcount;con++){
        	IDetailmessage message=this.iDetailMain.getmessage(con);
        	usemessage.add(message);
        }
        
    
		model.addObject("usemessage",usemessage)
		     .setViewName("tiles/includes/group-test");		
		return model;
	} 

	
	
}
