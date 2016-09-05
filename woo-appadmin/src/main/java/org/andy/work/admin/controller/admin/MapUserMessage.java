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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin")
public class MapUserMessage {

	@Resource
	private UserHelper userHelper;
	
	@Resource
	private IDetailmessageMain message;
	
	@Resource
	ICommentMain commentmain;
	
	@RequestMapping(value="/mapmessage")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView getmessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	String name=request.getParameter("usrname");
	Integer useid=Integer.valueOf(name);	
	IDetailmessage things=this.message.getmessage(useid);
    String thingsdesc=things.getthingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    
    Integer number=things.getnumber();
    IUser user=this.userHelper.getUserById(number);
    String usename=user.getUsername();
    String thingscomment="";
    
    IComment comment=this.commentmain.getcomment(useid);
    if(comment.getcomment()!=null){
    thingscomment=comment.getcomment();
    }
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingscomment", thingscomment);
    request.setAttribute("picname",things.getpicname());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/Mapmessage");
    return model;
	
	}
	
	
}
