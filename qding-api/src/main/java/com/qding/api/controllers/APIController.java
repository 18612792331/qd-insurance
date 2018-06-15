package com.qding.api.controllers;

import com.qding.api.call.Callable;
import com.qding.api.ip.IPUtil;
import com.qding.api.mock.Mocker;
import com.qding.api.process.GlobalInstance;
import com.qding.api.process.pool.ProtocolPool;
import com.qding.api.process.pool.ServicePool;
import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.process.print.JsonProtocolPrint;
import com.qding.api.process.security.TransportSecurity;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.ThreadLocalUtil;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.log.APILogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;


@Controller
@RequestMapping("/api")
public class APIController extends MultiActionController{



	@RequestMapping(value="{protocolAlias}/{serviceAlias}/{methodAlias}")
	public void handler(HttpServletRequest request, HttpServletResponse response,
			
			@PathVariable(value="protocolAlias") String protocolAlias, 
			@PathVariable(value="serviceAlias") String serviceAlias, 
			@PathVariable(value="methodAlias") String methodAlias) throws IOException {


		long startTime = System.currentTimeMillis();
		int code = 200;
		TransportSecurity transportSecurity = GlobalInstance.getTransportSecurity();
        String responseAsString = null;
		String responseAsStringToLog = null;
        AbstractProtocolPrint print = ApplicationContextUtil.getBeansOfType(JsonProtocolPrint.class);

        try {
        	
        	APILogger.getCurrRecord().setRequestFileds(new Date(), request.getRequestURI(), methodAlias, null, null, IPUtil.getIpAddress(request));
        	
        	transportSecurity.request(request);
        	
        	Class<? extends AbstractProtocolPrint> printClass = ProtocolPool.get(protocolAlias);
        	
        	if(printClass == null) {
				
        		throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "only support [json,xml] ");
        	}
        	else {
        		print = ApplicationContextUtil.getBeansOfType(printClass);
        	}
			
	        BaseRequest baseRequest = getBaseRequest(print, request);

	        Callable callable = getExecutor(serviceAlias, baseRequest);

	        Method executorMethod = ServicePool.getExecutorMethod(callable.getClass(), methodAlias);

			try {
				ThreadLocalUtil.getInstance().putBaseRequest(baseRequest);
				
				if(request.getParameter("mock") != null){
				    responseAsString = Mocker.mock(executorMethod.getGenericReturnType());
	            }else{
	                responseAsString = callable.call(print, executorMethod, request, response,baseRequest,methodAlias);
	            }
				
			} finally {
				ThreadLocalUtil.getInstance().remove();
			}
		} 
       
        catch(ServiceException e) {
			logger.error(e.getMessage(),e);
        	responseAsString = print.error(e.getReturnInfo().getCode(), e.getReturnInfo().getMessage());
        	code = e.getReturnInfo().getCode();
        }
        catch (Exception e) {
        	logger.error(e.getMessage(),e);
			responseAsString = print.error(500, "当前服务不可用，请稍后再试");
			code = 500;
		}

        try {
			responseAsStringToLog = responseAsString;
			responseAsString = transportSecurity.response(request, response, responseAsString);
		} catch (ServiceException e) {
			e.printStackTrace();
        	responseAsString = print.error(e.getReturnInfo().getCode(), e.getReturnInfo().getMessage());
		}
        

		if(!"".equals(responseAsString)){
			response.setCharacterEncoding("utf-8");
			response.setContentType(print.getContentType());
			response.getWriter().print(responseAsString);
		}

        long endTime = System.currentTimeMillis();
        APILogger.getCurrRecord().setResponseFileds(code, endTime - startTime,responseAsStringToLog);
        try {
			APILogger.doLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public BaseRequest getBaseRequest(AbstractProtocolPrint print, HttpServletRequest request) {
		
		Object body = GlobalInstance.getTransportSecurity().getParameter(request, "body");
		
		if(body != null) {
			return print.in(body.toString(), BaseRequest.class);
		}
		
		return null;
	}
	
	
	private Callable getExecutor(String serviceAlias, BaseRequest request) throws Exception {
		
		AppDevice appDevice = null;
		
		if(request != null) {
			
			appDevice = request.getAppDevice();
					
		}
		
		if(appDevice == null || appDevice.getQdVersion() == null) {
			
			logger.info("appDevice.version not found. instead of lastVersion");
			
			Class<? extends Callable> lastVersionExecutor = ServicePool.getLastVersion(serviceAlias);
			
			return ApplicationContextUtil.getBeansOfType(lastVersionExecutor);
		}

		return getExecutor(serviceAlias, appDevice.getQdVersion());
	}
	
	private Callable getExecutor(String serviceAlias, String version) throws Exception {
		
		Class<? extends Callable> clazz = ServicePool.get(serviceAlias, version);
		
		if(clazz != null) {
			return ApplicationContextUtil.getBeansOfType(clazz);
		}
		
		logger.info("alias : " + serviceAlias + ", version : " + version + " not register. instead of close version");
		
		Class<? extends Callable> closeVersionExecutor = ServicePool.getCloseVersion(serviceAlias, version);
		
		return ApplicationContextUtil.getBeansOfType(closeVersionExecutor);
	}





}
