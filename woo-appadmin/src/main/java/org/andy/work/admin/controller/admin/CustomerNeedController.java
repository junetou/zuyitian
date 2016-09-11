package org.andy.work.admin.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.controller.admin.form.DetailMessage;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Need;
import org.andy.work.appserver.service.INeedMain;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/admin/need")
public class CustomerNeedController {

	
	@Resource
	private INeedMain needmain;
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/show")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView show(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord){
		GridData<Need> grid = new GridData<Need>();
		search.setKeyWork(keyWord);
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<INeed> searchResp =this.needmain.SearchCustomerNeed(new SearchRequest<AcctUserSearchCriteria>(search,pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<Need> displays = new ArrayList<Need>();
			List<INeed> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				INeed user = userGroups.get(i);
				Need display = new Need();
				display.setNeed(user.getNeed());
				display.setName(user.getName());
				display.setDescs(user.getDescs());
				display.setPrice(user.getPrice());
				display.setDate(user.getDate());
				display.setKind(user.getKind());
				display.setOveranalyzed(user.getOveranalyzed());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/usr-customerneed");
		return model;
	}
	
	@RequestMapping(value="/addneed")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView AddNeed(ModelAndView model){
		model.setViewName("tiles/includes/addneedmap");		
		return model;
	} 
	
	@RequestMapping(value="/addmessage")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView AddMessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request,AcctUserSearchCriteria search)
	{		
		//获取本用户信息
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser User=this.userHelper.findUserByUsername(username);
		//获取网页传输过来的信息
		String thingsname=request.getParameter("thingsname");
		String thingsdesc=request.getParameter("thingsdesc");
		String thingsprice=request.getParameter("thingsprice");
		String thingsdate=request.getParameter("thingsdate");
		String thingskind=request.getParameter("thingskind");
		String thingsaddr=request.getParameter("thingsaddr");
		String thingsphone=request.getParameter("thingsphone");
		String thingslng=request.getParameter("lng");
		String thingslat=request.getParameter("lat");
		Integer thingsnumber=User.getId();
		Double lng=Double.valueOf(thingslng);
		Double lat=Double.valueOf(thingslat);
		Double price=Double.valueOf(thingsprice);
		//设置物品信息		

		Need mess=new Need();
		mess.setName(thingsname);
        mess.setDescs(thingsdesc);
        mess.setPrice(price);
        mess.setDate(thingsdate);
        mess.setLng(lat);
        mess.setLat(lng);
        mess.setKind(thingskind);
        mess.setNumber(thingsnumber);
        mess.setAddr(thingsaddr);
        mess.setPhone(thingsphone);
        mess.setOveranalyzed(1);//是否上线
        
        String judgement=this.needmain.addmessage(mess); //操作数据库
		if(judgement.equals("success")){		
			model.setViewName("tiles/includes/success");;
		}
		else{
			request.setAttribute("error", "提交错误，请重新输入");
			model.setViewName("tiles/includes/addneedmap");
		}
		return model;
	}
	
	@RequestMapping(value="/showmap")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView showmap(ModelAndView model){
		
        int count=this.needmain.checkcount();
        Integer newcount=Integer.valueOf(count);
        Integer con=1;
        List<INeed> usemessage=new ArrayList<INeed>();
        for(con=1;con<=newcount;con++){
        	INeed message=this.needmain.Search(con);
        	usemessage.add(message);
        } 
		model.addObject("usemessage",usemessage)
		     .setViewName("tiles/includes/group-customer");		
		return model;
	} 
	
	@RequestMapping(value="/showmessage")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView getmessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	String name=request.getParameter("usrname");
	Integer useid=Integer.valueOf(name);	
	INeed things=this.needmain.Search(useid);
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();  
    Integer number=things.getNumber();
    IUser user=this.userHelper.getUserById(number);
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/Needmessage");
    return model;
	}
	
	@RequestMapping(value="/showmyneed")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView ShowMyNeed(ModelAndView model,HttpServletResponse response,HttpServletRequest request,AcctUserSearchCriteria search){
		GridData<Need> grid = new GridData<Need>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser User=this.userHelper.findUserByUsername(username);
		Integer usenumber=User.getId();
		String keyword=request.getParameter("keyword");
		search.setKeyWork(keyword);
		SearchResponse<INeed> searchResp = this.needmain.SearchMy(new SearchRequest<AcctUserSearchCriteria>(search, pgm),usenumber);
		if (searchResp.getTotalRecords() > 0) {
			List<Need> displays = new ArrayList<Need>();
			List<INeed> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				INeed user = userGroups.get(i);
				Need display = new Need();
				display.setNeed(user.getNeed());
				display.setName(user.getName());
				display.setDescs(user.getDescs());
				display.setPrice(user.getPrice());
				display.setDate(user.getDate());
				display.setKind(user.getKind());
				display.setOveranalyzed(user.getOveranalyzed());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid)
		     .setViewName("tiles/includes/myneedthings");
		return model;
	}
	
	@RequestMapping(value="/newupdate/{need}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView NewUpdate(@PathVariable Integer need,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		Integer id=need;
		System.out.println(id);
		INeed mess=this.needmain.Search(id);
		Need form = new Need();
		form.setNeed(id);
		form.setDate(mess.getDate());
		form.setDescs(mess.getDescs());
		form.setName(mess.getName());
		form.setPhone(mess.getPhone());
		form.setPrice(mess.getPrice());
		form.setAddr(mess.getAddr());
		form.setNumber(mess.getNumber());
		form.setKind(mess.getKind());
		form.setLat(mess.getLat());
		form.setLng(mess.getLng());
		model.addObject("command", form);
		model.setViewName("tiles/includes/updateneedmessage");	
		return  model;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	private AjaxResponse update(Need userForm,ModelAndView model,HttpServletRequest request){
		Need user = this.getmessage(userForm);
		try {
			this.needmain.updatemessage(user);
		} catch (Exception e) {
			return AjaxResponse.fail(0);
		}
		return AjaxResponse.success();
	}
	
	private Need getmessage(Need userForm) {
	    
		Integer id=userForm.getNeed();
	    
	    Need user=this.needmain.Search(id);
	    Need use=new Need();
	    
	    Double price=Double.valueOf(userForm.getPrice());
	    
	    use.setNeed(user.getNeed());
	    use.setAddr(user.getAddr());
		use.setDate(userForm.getDate());
		use.setKind(user.getKind());
		use.setName(userForm.getName());
		use.setNumber(user.getNumber());
		use.setPhone(userForm.getPhone());
		use.setPrice(price);
		use.setDescs(userForm.getDescs());
		use.setLat(user.getLat());
		use.setLng(user.getLng());
	    use.setOveranalyzed(1);
		return use;
}
	
	@RequestMapping(value="/delete/{need}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView delete(@PathVariable Integer need,ModelAndView model,HttpServletRequest request,HttpServletResponse response,AcctUserSearchCriteria search){

		Integer id=need;
		Need uses=this.needmain.Search(id);
		Need use=new Need();
		    use.setNeed(uses.getNeed());
		    use.setAddr(uses.getAddr());
			use.setDate(uses.getDate());
			use.setKind(uses.getKind());
			use.setName(uses.getName());
			use.setNumber(uses.getNumber());
			use.setPhone(uses.getPhone());
			use.setPrice(uses.getPrice());
			use.setDescs(uses.getDescs());
			use.setLat(uses.getLat());
			use.setLng(uses.getLng());
		    use.setOveranalyzed(0);
		    this.needmain.updatemessage(use);

		GridData<Need> grid = new GridData<Need>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		IUser userid=this.userHelper.findUserByUsername(username);
		INeed User=this.needmain.Search(userid.getId());
		Integer usenumber=User.getNeed();
		String keyword=request.getParameter("keyword");
		SearchResponse<INeed> searchResp = this.needmain.SearchMy(new SearchRequest<AcctUserSearchCriteria>(search, pgm),usenumber);
		if (searchResp.getTotalRecords() > 0) {
			List<Need> displays = new ArrayList<Need>();
			List<INeed> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				INeed user = userGroups.get(i);
				Need display = new Need();
				display.setNeed(user.getNeed());
				display.setName(user.getName());
				display.setDescs(user.getDescs());
				display.setPrice(user.getPrice());
				display.setDate(user.getDate());
				display.setKind(user.getKind());
				display.setOveranalyzed(user.getOveranalyzed());
				displays.add(display);
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid)
		     .setViewName("tiles/includes/myneedthings");
		return  model;
	}
	
	
	@RequestMapping(value="/form/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public  ModelAndView show(@PathVariable Integer groupId,ModelAndView model,HttpServletResponse response,HttpServletRequest request){

		INeed things=this.needmain.Search(groupId);
		IUser use=this.userHelper.getUserById(things.getNumber());
		String thingsname=things.getName();
		String thingsprice=String.valueOf(things.getPrice());
		String thingslng=String.valueOf(things.getLng());
		String thingslat=String.valueOf(things.getLat());
		String thingsdesc=things.getDescs();
		String thingsdate=things.getDate();
		Integer thingsnumber=things.getNumber();
		String thingskind = things.getKind();
		String thingnumber=String.valueOf(thingsnumber);
		String thingsplace=things.getAddr();
		
		
		request.setAttribute("thingsname", thingsname);
		request.setAttribute("thingsprice", thingsprice);
		request.setAttribute("thingslng", thingslng);
		request.setAttribute("thingslat",thingslat);
		request.setAttribute("thingsdesc", thingsdesc);
		request.setAttribute("thingsdate", thingsdate);
		request.setAttribute("thingskind",thingskind);
		request.setAttribute("thingsnumber", use.getUsername());
		request.setAttribute("thingsplace", thingsplace);
		request.setAttribute("thingsid", things.getNeed());
		model.setViewName("tiles/includes/needandmessage");
		
		return model;
	}
	
	
	
}
