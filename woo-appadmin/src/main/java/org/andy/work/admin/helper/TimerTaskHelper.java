package org.andy.work.admin.helper;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskHelper {
	
	private static final Logger log = Logger.getLogger(TimerTaskHelper.class);
	
	private static Timer timer;
	private static final Map<String, TimerTask> taskMap = new ConcurrentHashMap<String, TimerTask>();
	
	private Timer getTimer(){
		if (timer == null) {
			synchronized (this) {
				if (timer == null) {
					timer = new Timer();
				}
			}
		}
		return timer;
	}
	
	/**
	 * 取消过期订单
	 * @param exprieDays
	 * @return
	 */
	private int cancelExpriedOrders(Integer exprieDays) {
		return 0;//this.orderMaintenanceService.cancelExpriedOrders(exprieDays);
	}
	
	/**
	 * 过期订单处理
	 * @param exprieDays
	 *  ---------------------------------------------------------------------------------------
	 */
	public void startOrderExprieTask(Integer exprieDays) {
		log.info("过期订单处理任务准备中...");
		if (exprieDays == null) {
			String systemSettingValue = "";//systemHelper.getSystemSettingValue(SystemSettingKey.ORDER_EXPDATE);
			if (StringUtil.hasValue(systemSettingValue)) {
				exprieDays = Integer.valueOf(systemSettingValue);
			}else {
				log.info("过期订单处理任务--准备失败：无效的过期天数：" + exprieDays);
				return;
			}			
		}
		final Integer days = exprieDays;
		log.info("过期订单处理任务--过期天数："+days);
		TimerTask task = null;
		if (taskMap.containsKey("orderExprieTask")) {
			task = taskMap.remove("orderExprieTask");
			task.cancel();
			log.info("过期订单处理任务 -- 关闭。");
		}
		
		task = new TimerTask() {
			@Override
			public void run() {
				try {
					log.info("过期订单处理任务 -- 开始。");
					int count = TimerTaskHelper.this.cancelExpriedOrders(days);
					log.info("过期订单处理任务 -- 完成，处理过期订单 "+ count +"个。");
				} catch (Exception e) {
					log.info("过期订单处理任务 -- 异常:"+e.getMessage(),e);
					this.cancel();
					log.info("过期订单处理任务 -- 终止。");
				}
			}
		};
		Timer t = this.getTimer();
		int hour = 3 ;
		int second = 0;
		long period = 24 * 3600 * 1000;
		Date firstDate =  this.orderExprieTaskStartDate(hour, second);//每天 3:00 执行，24小时制
		t.scheduleAtFixedRate(task,firstDate, period);//间隔 24 小时执行
		taskMap.put("orderExprieTask", task);
		String dateStr = CommonUtils.datetimeFormat(firstDate);
		log.info("过期订单处理任务准备就绪，开始时间："+ dateStr +"，周期: 24 小时。");
	}
	
	public void stopOrderExprieTask() {
		if (taskMap.containsKey("orderExprieTask")) {
			taskMap.remove("orderExprieTask");
			log.info("过期订单处理任务 -- 已停止。");
		}
	}
	
	/**
	 * 获取订单过期任务的开始时间
	 * @param hourOfDay 开始的时间，时，24小时制
	 * @param minute 开始的时间，分
	 * @return 返回设置的时间
	 */
	private Date orderExprieTaskStartDate(int hourOfDay, int minute){
		log.info("过期订单处理任务--开始设置执行时间。");
		//设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, hourOfDay, minute, 0);
        Date date = calendar.getTime();
        if (date.getTime() < new Date().getTime()) {
        	day += 1;
        	calendar.set(year, month, day, hourOfDay, minute, 0);
			date = calendar.getTime();
			log.info("过期订单处理任务 -- 今天指定时间 "+hourOfDay+":"+minute+" 已过，待下一个指定时间执行。");
		}
        
        log.info("过期订单处理任务--执行时间设置已完成。");
        return date;
	}
	
	/**
	 * 自动确认收货订单，交易完成
	 * @param exprieDays
	 *  ---------------------------------------------------------------------------------------
	 */
	public void startOrderConfirmTask(Integer exprieDays) {
		log.info("自动确认收货订处理任务准备中...");
		if (exprieDays == null) {
			String systemSettingValue = "";//systemHelper.getSystemSettingValue(SystemSettingKey.ORDER_CONFIRM);
			if (StringUtil.hasValue(systemSettingValue)) {
				exprieDays = Integer.valueOf(systemSettingValue);
			}else {
				log.info("自动确认收货订处理任务--准备失败：无效的天数：" + exprieDays);
				return;
			}			
		}
		final Integer days = exprieDays;
		log.info("自动确认收货订处理任务--天数："+days);
		TimerTask task = null;
		if (taskMap.containsKey("orderConfirmTask")) {
			task = taskMap.remove("orderConfirmTask");
			task.cancel();
			log.info("自动确认收货订处理任务 -- 关闭。");
		}
		
		task = new TimerTask() {
			@Override
			public void run() {
				try {
					log.info("自动确认收货订处理任务 -- 开始。");
					int count = TimerTaskHelper.this.orderConfirmTaskStartDate(days);
					log.info("自动确认收货订处理任务 -- 完成，处理自动确认收货订 "+ count +"个。");
				} catch (Exception e) {
					log.info("自动确认收货订处理任务 -- 异常:"+e.getMessage(),e);
					this.cancel();
					log.info("自动确认收货订处理任务 -- 终止。");
				}
			}
		};
		Timer t = this.getTimer();
		int hour = 3;
		int second = 0;
		long period = 24 * 3600 * 1000;
		Date firstDate =  this.orderConfirmTaskStartDate(hour, second);//每天 3:00 执行，24小时制
		t.scheduleAtFixedRate(task,firstDate, period);//间隔 24 小时执行
		taskMap.put("orderConfirmTask", task);
		String dateStr = CommonUtils.datetimeFormat(firstDate);
		log.info("自动确认收货订处理任务准备就绪，开始时间："+ dateStr +"，周期: 24 小时。");
	}
	
	/**
	 * 自动确认收货订
	 * @param days
	 * @return
	 */
	public int orderConfirmTaskStartDate(Integer days) {
		return 0;//this.orderMaintenanceService.orderConfirmTaskStartDate(days);
	}

	public void stopOrderConfirmTask() {
		if (taskMap.containsKey("orderConfirmTask")) {
			taskMap.remove("orderConfirmTask");
			log.info("自动确认收货订处理任务 -- 已停止。");
		}
	}
	
	/**
	 * 获取自动确认收货订任务的开始时间
	 * @param hourOfDay 开始的时间，时，24小时制
	 * @param minute 开始的时间，分
	 * @return 返回设置的时间
	 */
	private Date orderConfirmTaskStartDate(int hourOfDay, int minute){
		log.info("自动确认收货订理任务--开始设置执行时间。");
		//设置执行时间
       Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH);
       int day = calendar.get(Calendar.DAY_OF_MONTH);
       calendar.set(year, month, day, hourOfDay, minute, 0);
       Date date = calendar.getTime();
       if (date.getTime() < new Date().getTime()) {
       	day += 1;
       	calendar.set(year, month, day, hourOfDay, minute, 0);
			date = calendar.getTime();
			log.info("自动确认收货订处理任务 -- 今天指定时间 "+hourOfDay+":"+minute+" 已过，待下一个指定时间执行。");
		}
       
       log.info("自动确认收货订处理任务--执行时间设置已完成。");
       return date;
	}
	
	
}