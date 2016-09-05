package org.andy.work.admin.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;


@Controller
@RequestMapping("/portal/admin/addmessage")
public class AddDetailMessage extends HttpServlet {

	
	@Resource
	IDetailmessageMain message;
	
	@Resource
	ICommentMain comment;
	
	@Resource
	private IDetailmessageMain iDetailMain; 
	
	@Resource
	private UserHelper userHelper;
	
	File tempPathFile;  
	
	@RequestMapping(value="/message")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView addmessage(@RequestParam("file0") MultipartFile upLoadFile,ModelAndView model,HttpServletResponse response,HttpServletRequest request,AcctUserSearchCriteria search)
	throws ServletException, IOException
	{		

		//获取本用户信息
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser User=this.userHelper.findUserByUsername(username);
		String filename=upLoadFile.getOriginalFilename();
		String extensionName=StringUtils.substringAfterLast(filename, ".");
		//获取网页传输过来的信息
		int z=this.iDetailMain.checkcount();
		z=z+1;
		String thingsname=request.getParameter("thingsname");
		String thingsdesc=request.getParameter("thingsdesc");
		String thingsprice=request.getParameter("thingsprice");
		String thingsdate=request.getParameter("thingsdate");
		String thingskind=request.getParameter("thingskind");
		String thingsaddr=request.getParameter("thingsaddr");
		String thingsphone=request.getParameter("thingsphone");
		String thingslng=request.getParameter("lng");
		String thingslat=request.getParameter("lat");
		String url="newfileName"+userDetails.getUsername()+z+"."+extensionName;
		Integer thingsnumber=User.getId();
		Double lng=Double.valueOf(thingslng);
		Double lat=Double.valueOf(thingslat);
		Double price=Double.valueOf(thingsprice);
		//设置物品信息		

		Detailmessage mess=new Detailmessage();
		mess.setName(thingsname);
        mess.setthingsDesc(thingsdesc);
        mess.setPrice(price);
        mess.setDate(thingsdate);
        mess.setthingsLng(lat);
        mess.setthingsLat(lng);
        mess.setkind(thingskind);
        mess.setnumber(thingsnumber);
        mess.setaddr(thingsaddr);
        mess.setphone(thingsphone);
        mess.setoveranalyzed(1);//是否上线
        mess.setpicname(url);;
        
        String judgement=message.addmessage(mess); //操作数据库
		if(judgement.equals("success")){	
			String pathurl="H:\\new\\staticResources\\static\\picture";
			int n=this.iDetailMain.checkcount();
			Map <String,String> map = new HashMap<String,String>();
	        map.put("jpg", "jpg");
	        map.put("jpeg", "jpeg");
	        map.put("bmp", "bmp");
	        map.put("gif", "gif");
	        File file = new File(pathurl+"\\newfileName"+userDetails.getUsername()+n+"."+extensionName);
         	upLoadFile.transferTo(file);
			Integer thingsid=mess.getthingsId();
			Comment comments=new Comment();
			comments.setthingsid(n);
			comments.setcomment("");
			comments.seteverythingnumber(n);
			Date times = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			comments.settime(sdf.format(times));
			comment.addcomment(comments);
			
			
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
			
		}
		else{
			request.setAttribute("error", "提交错误，请重新输入");
			model.setViewName("tiles/includes/addmap");
		}

		
		/*
		String pathurl1="G:\\html";
		String filename1=upLoadFile1.getOriginalFilename();
		String extensionName1=StringUtils.substringAfterLast(filename1, ".");		
		Map <String,String> map1 = new HashMap<String,String>();
        map1.put("jpg", "jpg");
        map1.put("jpeg", "jpeg");
        map1.put("bmp", "bmp");
        map1.put("gif", "gif");
        if (null == filename1 || 0 == filename1.length()) {
           System.out.println("必需输入");	
        } 
        else if (!map1.containsKey(extensionName1)) {
           System.out.println("a");
         } 
         else{
         	File file1 = new File(pathurl1+"\\newfileName"+"."+extensionName1);
         	upLoadFile1.transferTo(file1);
         	}
        */
		return model;
	}
		
	
}
