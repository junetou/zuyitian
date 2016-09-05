package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.ITrade;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Trade;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.ITradeMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/portal/admin/trade")
public class TradeController {
	
	@Resource
	private ITradeMain trademain;
	
	@Resource
	private UserHelper userhelper;
	
	@Resource
	private IDetailmessageMain detailmessage;
	
	@Resource
	ICommentMain commentmain;
	
	//查看自己申请借的东西
	@RequestMapping(value="/borrow")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
    public ModelAndView searchborrow(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp=this.trademain.searchborrow(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0) {
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setTrade(user.getTrade());
			    display.setAssign(user.getAssign());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/includes/borrow");
		return model;
	}
	
	//查看人家要租借自己的东西
	@RequestMapping(value="/seller")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
    public ModelAndView searchseller(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp=this.trademain.searchseller(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0) {
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setTrade(user.getTrade());
			    display.setBorrowname(user.getBorrowname());
			    display.setGoodsname(user.getGoodsname());
			    display.setAssign(user.getAssign());
			    display.setEnsure(user.getEnsure());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/includes/seller");
		return model;
	}
	
	//租借
	@RequestMapping(value="/buy")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView buy(ModelAndView model, HttpServletRequest request,HttpServletResponse response){
		AdminUserDetails userdetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String thingsid=request.getParameter("thingsid");
		Integer thingsnumber=Integer.valueOf(thingsid);
		IDetailmessage message=this.detailmessage.getmessage(thingsnumber);
	    Integer seller=message.getnumber();
	    IUser sellername=this.userhelper.getUserById(seller);
	    IUser borrowid=this.userhelper.findUserByUsername(userdetails.getUsername());
		Trade trade=new Trade();
		trade.setBorrow(borrowid.getId());
		trade.setBorrowname(userdetails.getName());
		trade.setSeller(sellername.getId());;
		trade.setSellername(sellername.getDisplayName());
		trade.setThing(message.getthingsId());
		trade.setGoodsname(message.getName());
		trade.setAssign(1);
		trade.setEnsure(0);
		trade.setSuccess(0);
		String judge=this.trademain.addmessage(trade);	
		request.setAttribute("judge", judge);
		model.setViewName("tiles/includes/handle");	
		return model;
	}
	
	//卖者确认租借
	@RequestMapping(value="/ensure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView sellerEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		Trade trade=this.trademain.getTrade(groupId);
		Trade newtrade=new Trade();
		newtrade.setEnsure(1);
		newtrade.setAssign(trade.getAssign());
		newtrade.setBorrow(trade.getBorrow());
		newtrade.setBorrowname(trade.getBorrowname());
		newtrade.setGoodsname(trade.getGoodsname());
		newtrade.setSeller(trade.getSeller());
		newtrade.setSellername(trade.getSellername());
		newtrade.setSuccess(1);
		newtrade.setThing(trade.getThing());
		newtrade.setTrade(trade.getTrade());
		String judge=this.trademain.updatemessage(newtrade);	
		IDetailmessage mess=this.detailmessage.getmessage(trade.getThing());
		Detailmessage message=new Detailmessage();
		message.setaddr(mess.getaddr());
		message.setDate(mess.getDate());
		message.setkind(mess.getkind());
		message.setName(mess.getName());
		message.setnumber(mess.getnumber());
		message.setoveranalyzed(0);
		message.setphone(mess.getphone());
		message.setpicname(mess.getpicname());
		message.setPrice(mess.getPrice());
		message.setthingsDesc(mess.getthingsDesc());
		message.setthingsId(mess.getthingsId());
		message.setthingsLat(mess.getthingsLat());
		message.setthingsLng(mess.getthingsLng());
		this.detailmessage.updatemessage(message);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/includes/handle");	
		return model;
	}
	
	//卖者不租借
	@RequestMapping(value="/noensure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView sellerNoEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		Trade trade=this.trademain.getTrade(groupId);
		Trade newtrade=new Trade();
		newtrade.setEnsure(0);
		newtrade.setAssign(trade.getAssign());
		newtrade.setBorrow(trade.getBorrow());
		newtrade.setBorrowname(trade.getBorrowname());
		newtrade.setGoodsname(trade.getGoodsname());
		newtrade.setSeller(trade.getSeller());
		newtrade.setSellername(trade.getSellername());
		newtrade.setSuccess(trade.getSuccess());
		newtrade.setThing(trade.getThing());
		newtrade.setTrade(trade.getTrade());
		String judge=this.trademain.updatemessage(newtrade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/includes/handle");	
		return model;
	}
	
	//租者取消租借
	@RequestMapping(value="/cancelborrow/{tradeid}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView cancelborrow(@PathVariable Integer tradeid,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		Trade trade=this.trademain.getTrade(tradeid);
		Trade newtrade=new Trade();
		newtrade.setAssign(0);
		newtrade.setEnsure(trade.getEnsure());
		newtrade.setBorrow(trade.getBorrow());
		newtrade.setBorrowname(trade.getBorrowname());
		newtrade.setGoodsname(trade.getGoodsname());
		newtrade.setSeller(trade.getSeller());
		newtrade.setSellername(trade.getSellername());
		newtrade.setSuccess(trade.getSuccess());
		newtrade.setThing(trade.getThing());
		newtrade.setTrade(trade.getTrade());
		String judge=this.trademain.updatemessage(newtrade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/includes/handle");	
		return model;
	}
	
	//正在交易情况
	@RequestMapping(value="/tradeing")
	@ResponseBody
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView Tradeing(ModelAndView model,HttpServletRequest request,HttpServletResponse response,AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp=this.trademain.searchsuccess(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		SearchResponse<ITrade> newsearchResp=this.trademain.searchsuccessSeller(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0) {
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
			}
			grid.setDatas(displays);
			pgm.setTotalRecord(searchResp.getTotalRecords());
			PagingHelper.setPaging(pgm, grid);
		}
		else if(newsearchResp.getTotalRecords()>0)
		{
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = newsearchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
			}
			grid.setDatas(displays);	
			pgm.setTotalRecord(newsearchResp.getTotalRecords());
			PagingHelper.setPaging(pgm, grid);
		}
		else{
			pgm.setTotalRecord(newsearchResp.getTotalRecords());
			PagingHelper.setPaging(pgm, grid);
		}
		model.addObject("grid", grid).setViewName("tiles/includes/tradeing");
		return model;
		
	}
	

	
	@RequestMapping(value="/detailmessage/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView getmessage(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	Integer useid=thing;	
	IDetailmessage things=this.detailmessage.getmessage(useid);
	IUser useweixin=this.userhelper.getUserById(things.getnumber());
    String thingsdesc=things.getthingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    Integer number=things.getnumber();
    IUser user=this.userhelper.getUserById(number);
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
    request.setAttribute("thingsphone", things.getphone());
    request.setAttribute("thingsnumber", usename);
    request.setAttribute("useweixin",useweixin.getWeixin());
    model.setViewName("tiles/includes/detailmessage");
    return model;
	
	}
	
	@RequestMapping(value="/tradesuccess/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMMENT, operationType=AuthOperationConfiguration.ADMIN_ACCOUNT_VIEW)
	public ModelAndView tradesuccess(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	Trade trade=this.trademain.getTrade(thing);
	Trade newtrade=new Trade();
	newtrade.setAssign(3);
	newtrade.setEnsure(3);
	newtrade.setBorrow(trade.getBorrow());
	newtrade.setBorrowname(trade.getBorrowname());
	newtrade.setGoodsname(trade.getGoodsname());
	newtrade.setSeller(trade.getSeller());
	newtrade.setSellername(trade.getSellername());
	newtrade.setSuccess(3);
	newtrade.setThing(trade.getThing());
	newtrade.setTrade(trade.getTrade());
	String judge=this.trademain.updatemessage(newtrade);
	request.setAttribute("judge", judge);
    model.setViewName("tiles/includes/success");
    return model;
	
	}
	
}
