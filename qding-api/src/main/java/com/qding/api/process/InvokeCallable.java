package com.qding.api.process;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.process.security.SecurityObject;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 
 * @author lichao
 *
 */
public abstract class InvokeCallable {
	
	protected abstract Object[] beforeInvoke(AbstractProtocolPrint print, Method targetMethod, Object securityObject, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	protected abstract Object invoke(Method targetMethod, Object[] targetMethodArguments, SecurityObject securityObject, HttpServletRequest request, HttpServletResponse response,  Integer version) throws Exception;
	
	protected abstract Object afterInvoke(AbstractProtocolPrint print, Object value);
}
