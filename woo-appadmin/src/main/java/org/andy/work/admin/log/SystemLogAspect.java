package org.andy.work.admin.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.appserver.model.ISystemLogs;
import org.andy.work.appserver.model.impl.SystemLogs;
import org.andy.work.appserver.service.ICommonMaintenanceService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLogAspect {

	@Resource HttpServletRequest request;
	
	@Resource UserSessionHelper sessionHelper;
	
	@Resource ICommonMaintenanceService commonService;

	private static final Logger logger = Logger.getLogger(SystemLogAspect.class);

	@Pointcut("@annotation(org.andy.work.admin.log.SystemLog)")
	public void controllerAspect() {}

	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		String addressIp = this.getRemoteHost(request);
		try {
			ISystemLogs logs = new SystemLogs();
			logs.setCreateBy(sessionHelper.getCurrentUserName());
			logs.setContent(getServiceMethodDescription(joinPoint));
			logs.setCreateDate(new Date());
			logs.setLogsType(getServiceMethodType(joinPoint));
			logs.setAddressIp(addressIp);
			this.commonService.makePersist(logs);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	public static String getServiceMethodType(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String type = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					type = method.getAnnotation(SystemLog.class).type();
					break;
				}
			}
		}
		return type;
	}
	
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
