package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.controller.admin.detail.UserDetail;
import org.andy.work.admin.controller.admin.form.DetailMessage;
import org.andy.work.admin.controller.admin.form.UserForm;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
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
import org.andy.work.utils.AjaxResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin/comment")
public class CommentController {

	@Resource
	ICommentMain commentmain;
	
	@Resource
	private IDetailmessageMain message;
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/showcomment")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView comment(ModelAndView model,HttpServletResponse response,HttpServletRequest request,AcctUserSearchCriteria search){
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser User=this.userHelper.findUserByUsername(username);
		Integer usenumber=User.getId();
		String keyword=request.getParameter("keyword");
		SearchResponse<IDetailmessage> searchResp = this.userHelper.searchMessage(new SearchRequest<AcctUserSearchCriteria>(search, pgm),usenumber,keyword);
		if (searchResp.getTotalRecords() > 0) {
			List<MessageDetail> displays = new ArrayList<MessageDetail>();
			List<IDetailmessage> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IDetailmessage user = userGroups.get(i);
				MessageDetail display = new MessageDetail();
				display.setThingsId(user.getthingsId());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getthingsDesc());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getkind());
				display.setThingsoveranalyzed(user.getoveranalyzed());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid)
		     .setViewName("tiles/includes/myofthings");
		return model;
	}
	
	@RequestMapping(value="/form/{thingsId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView seecomment(@PathVariable Integer thingsId,ModelAndView model,HttpServletRequest request,HttpServletResponse response,AcctUserSearchCriteria search){
		GridData<Comment> grid = new GridData<Comment>();
		String keyword=String.valueOf(thingsId);
		search.setKeyWork(keyword);
		Integer name=new Integer("1");//初始化
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IComment> searchResp = this.userHelper.searchcomment(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<Comment> displays = new ArrayList<Comment>();
			List<IComment> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IComment comment = userGroups.get(i);
				Comment display = new Comment();
		        display.setcommentid(comment.getcommentid());
		        display.setcomment(comment.getcomment());
				displays.add(display);
		        name=comment.geteverythingnumber();
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		IDetailmessage thingname=message.getmessage(name);
		String thingsname=thingname.getName();
        request.setAttribute("thingsname", thingsname);
		model.addObject("grid", grid).setViewName("tiles/includes/showcomment");
		return model;
		
	}
	
	@RequestMapping(value="/newupdate/{thingsId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView seecomment(@PathVariable Integer thingsId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		Integer id=thingsId;
		IDetailmessage mess=message.getmessage(id);
		DetailMessage form = new DetailMessage();
		form.setId(id);
		form.setDate(mess.getDate());
		form.setDesc(mess.getthingsDesc());
		form.setName(mess.getName());
		form.setPhone(mess.getphone());
		form.setPrice(mess.getPrice().toString());
		form.setAddr(mess.getaddr());
		form.setNumber(mess.getnumber());
		form.setKind(mess.getkind());
		form.setLat(mess.getthingsLat());
		form.setLng(mess.getthingsLng());
        form.setPicname(mess.getpicname());
        request.setAttribute("picname", mess.getpicname());
		model.addObject("command", form);
		model.setViewName("tiles/includes/updatemessage");	
		return  model;
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	private AjaxResponse update(DetailMessage userForm,ModelAndView model,HttpServletRequest request){
		Detailmessage user = this.getmessage(userForm);
		try {
			this.message.updatemessage(user);
		} catch (Exception e) {
			return AjaxResponse.fail(0);
		}
		return AjaxResponse.success();
	}
	
	private Detailmessage getmessage(DetailMessage userForm) {
	    Integer id=userForm.getId();
        System.out.println(id);
	    
	    Detailmessage user=this.message.getmessage(id);
	    Detailmessage use=new Detailmessage();
	    
	    Double price=Double.valueOf(userForm.getPrice());
	    
	    use.setthingsId(user.getthingsId());
	    use.setaddr(user.getaddr());
		use.setDate(userForm.getDate());
		use.setkind(user.getkind());
		use.setName(userForm.getName());
		use.setnumber(user.getnumber());
		use.setphone(userForm.getPhone());
		use.setPrice(price);
		use.setthingsDesc(userForm.getDesc());
		use.setthingsId(user.getthingsId());
		use.setthingsLat(user.getthingsLat());
		use.setthingsLng(user.getthingsLng());
	    use.setoveranalyzed(1);
	    use.setpicname(user.getpicname());
		return use;
}
	
	@RequestMapping(value="/delete/{thingsId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView delete(@PathVariable Integer thingsId,ModelAndView model,HttpServletRequest request,HttpServletResponse response,AcctUserSearchCriteria search){

		Integer id=thingsId;
		Detailmessage uses=message.getmessage(id);
		Detailmessage use=new Detailmessage();
		 use.setthingsId(uses.getthingsId());
		    use.setaddr(uses.getaddr());
			use.setDate(uses.getDate());
			use.setkind(uses.getkind());
			use.setName(uses.getName());
			use.setnumber(uses.getnumber());
			use.setphone(uses.getphone());
			use.setPrice(uses.getPrice());
			use.setthingsDesc(uses.getthingsDesc());
			use.setthingsId(uses.getthingsId());
			use.setthingsLat(uses.getthingsLat());
			use.setthingsLng(uses.getthingsLng());
		    use.setoveranalyzed(0);
		    use.setpicname(uses.getpicname());
		    this.message.updatemessage(use);

		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser User=this.userHelper.findUserByUsername(username);
		Integer usenumber=User.getId();
		String keyword=request.getParameter("keyword");
		SearchResponse<IDetailmessage> searchResp = this.userHelper.searchMessage(new SearchRequest<AcctUserSearchCriteria>(search, pgm),usenumber,keyword);
		if (searchResp.getTotalRecords() > 0) {
			List<MessageDetail> displays = new ArrayList<MessageDetail>();
			List<IDetailmessage> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IDetailmessage user = userGroups.get(i);
				MessageDetail display = new MessageDetail();
				display.setThingsId(user.getthingsId());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getthingsDesc());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getkind());
				display.setThingsoveranalyzed(user.getoveranalyzed());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid)
		     .setViewName("tiles/includes/myofthings");
		return  model;
	}
	
	
	
}
