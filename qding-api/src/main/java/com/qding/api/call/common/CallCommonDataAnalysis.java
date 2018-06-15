package com.qding.api.call.common;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.common.struct.dataAnalysis.request.StartupAppRequest;
import com.qding.api.imessage.passport.LoginExtMessage;
import com.qding.api.imessage.passport.LoginExtMessageParams;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据分析
 * @author lichao
 *
 */
public class CallCommonDataAnalysis extends Callable{

    @Autowired
    private LoginExtMessage loginExtMessage;

	private static Logger logger = Logger.getLogger(CallCommonDataAnalysis.class);

	/**
	 * 启动APP
	 * @param request
	 * @param httpServletRequest
	 * @return
	 */
	@HTTP(alias="startupApp")
	public Response<ResponseData> startupApp(StartupAppRequest request, HttpServletRequest httpServletRequest) {
		
		Response<ResponseData> response = new Response<>();
		try {
            LoginExtMessageParams params = new LoginExtMessageParams();
            params.setType(1);
            params.setMobile(request.getMobile());
            params.setAccountId(request.getAccountId());
            params.setOsVersion(request.getAppDevice().getOSVersion());
            params.setQdDevice(request.getAppDevice().getQdDevice());
            params.setQdPlatform(request.getAppDevice().getQdPlatform());
            params.setQdVersion(request.getAppDevice().getQdVersion());
            params.setDeviceNo(request.getAppDevice().getDeviceId());
            logger.info("paramJsonStr:"+params);
            loginExtMessage.addLoginExt(params);

		}catch (Exception ex){

			logger.error("method :startupApp is error!",ex);
		}
		response.setData(new ResponseData());
		
		return response;
	}
}
