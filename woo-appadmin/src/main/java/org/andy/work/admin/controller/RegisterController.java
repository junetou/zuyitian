package org.andy.work.admin.controller;

import java.io.IOException;








import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;












import org.andy.work.admin.helper.CookieHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.security.HmacPasswordEncoderImpl;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.model.impl.UserGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterController {

	@Resource
	private CookieHelper cookieHelper;
	
	@RequestMapping(value="/register/showregister")
	public ModelAndView register(ModelAndView model){
        model.setViewName("register");
        return model;
	}
	
	@Resource
	private UserHelper userhelper;
	

	
	@RequestMapping(value="/register/createofregister",method=RequestMethod.POST)
	public ModelAndView createofregister(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		User use=new User();
     	Date date=new Date();
     	HmacPasswordEncoderImpl pw=new HmacPasswordEncoderImpl();
        String usrname=request.getParameter("usrname");
        UserGroup usrgroup=new UserGroup();
        usrgroup.setId(2);
		usrgroup.setName("用户");
		usrgroup.setPermission("ADMIN_ACCOUNT_VIEW");
        usrgroup.setRole("ROLE_COMMENT");
        
        if(userhelper.hasusrname(usrname)){
		String password=request.getParameter("password");
		String newpassword=pw.encodePassword(password, password);
		String name=request.getParameter("name");
		use.setPassword(newpassword);
		use.setUsername(usrname);
		use.setLocked("N");
		use.setDisplayName(name);
		use.setCreatedDate(date);
		use.setStaffNum("0635008");
		use.setUserGroup(usrgroup);
		userhelper.saveUser(use);
		model.setViewName("login");
		}
		else{
			request.setAttribute("error", "此账户已存在，请更换");
			model.setViewName("register");
		}
		return model;
	
	}
	

}
