package com.qding.api.process.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.exception.ServiceException;

public abstract class CallableSecurity {

	public abstract SecurityObject checkCallableSecurity(AbstractProtocolPrint print,
			Method targetMethod, HttpServletRequest request,
			HttpServletResponse response, BaseRequest baseRequest) throws ServiceException;
	
}
