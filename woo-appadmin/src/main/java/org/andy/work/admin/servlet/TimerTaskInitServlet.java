package org.andy.work.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.helper.TimerTaskHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class TimerTaskInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		new Thread(){
			@Override
			public void run() {
				WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
				TimerTaskHelper timerTaskHelper = (TimerTaskHelper) applicationContext.getBean("timerTaskHelper");
				//启动处理过期订单
				timerTaskHelper.startOrderExprieTask(null);
				//启动处理自动确认收货订单，交易完成
				timerTaskHelper.startOrderConfirmTask(null);
			}
		}.start();
		
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
