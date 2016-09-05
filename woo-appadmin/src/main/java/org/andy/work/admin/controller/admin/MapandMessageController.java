package org.andy.work.admin.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value="/portal/admin/thingsandmap")
public class MapandMessageController {
	
	@Resource
	private IDetailmessageMain message;
	
	@Resource
	private UserHelper userHelper;
	
	@Resource
	ICommentMain commentmain;
	
	@RequestMapping(value="/form/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public  ModelAndView show(@PathVariable Integer groupId,ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		IComment comment=this.commentmain.getcomment(groupId);
		IDetailmessage things=this.message.getmessage(groupId);
		IUser use=this.userHelper.getUserById(things.getnumber());
		String thingsname=things.getName();
		String thingsprice=String.valueOf(things.getPrice());
		String thingslng=String.valueOf(things.getthingsLng());
		String thingslat=String.valueOf(things.getthingsLat());
		String thingsdesc=things.getthingsDesc();
		String thingsdate=things.getDate();
		Integer thingsnumber=things.getnumber();
		String thingscomment=comment.getcomment();
		String commentdate=comment.gettime();
		String thingskind = things.getkind();
		String thingnumber=String.valueOf(thingsnumber);
		String thingsplace=things.getaddr();
		
		String picname1="<woo:url value='/static/picture/";
        String picname2=things.getpicname();
		String picname3="'>";
		String picname=picname2;

		
		request.setAttribute("thingsname", thingsname);
		request.setAttribute("thingsprice", thingsprice);
		request.setAttribute("thingslng", thingslng);
		request.setAttribute("thingslat",thingslat);
		request.setAttribute("thingsdesc", thingsdesc);
		request.setAttribute("thingsdate", thingsdate);
		request.setAttribute("thingscomment", thingscomment);
		request.setAttribute("commentdate",commentdate);
		request.setAttribute("thingskind",thingskind);
		request.setAttribute("thingsnumber", use.getUsername());
		request.setAttribute("thingsplace", thingsplace);
		request.setAttribute("picname", picname);
		request.setAttribute("thingsid", things.getthingsId());
		model.setViewName("tiles/includes/mapandmessage");
		
		return model;
	}
	
	

}
