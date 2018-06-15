package com.qding.api.sms;

import java.util.Arrays;
import java.util.Map;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.message.struct.request.MsgVoiceVerfyCodeRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.MsgSendByMobileRequest;
import com.qding.message.struct.response.MsgSendResponse;

/**
 * 发送短信
 * @author lichao
 *
 */
public class SendSms {

	private static Logger logger = Logger.getLogger("sendSms");
	
	@Autowired
	private IMessageService messageService;


	public boolean  sendVoiceVerfyCode (String mobile,String verfyCode) {

		MsgVoiceVerfyCodeRequest request = new MsgVoiceVerfyCodeRequest();
		request.setMobile(mobile);
		request.setVerfyCode(verfyCode);
		logger.info(request);
		BaseResponse response = messageService.sendVoiceVerifyCode(request);
		logger.info(response);
		int responseCode = response.getReturnInfo().getCode();

		if(responseCode != HttpStatus.OK.getStatusCode()) {
			return false;
		}
		return true;

	}
	
	public boolean send(SmsAction action, String mobile, Map<String, String> data) throws ServiceException {
		
		String templateId = SmsAction.getSmsTemplateId(action.getAction());
		
		return send(templateId, mobile, data);
	}
	
	public boolean send(String templateId, String mobile, Map<String, String> data) {
		
		MsgSendByMobileRequest request = new MsgSendByMobileRequest(
				Arrays.asList(mobile), templateId, data);
		
		logger.info(request);
	
		MsgSendResponse response = messageService.sendMessageByMobiles(
				request
		);
		
		logger.info(response);
		
		int responseCode = response.getReturnInfo().getCode();
		
		if(responseCode != HttpStatus.OK.getStatusCode()) {
			return false;
		}
		
		return true;
	}
	
	public String getSecretMobile(String mobile, String replacement) {
		
		if(!QDStringUtil.isMobile(mobile)) {
			
			return mobile;
		}
		
		return mobile.replaceAll("(\\d{3}).*(\\d{4})", "$1"+ new String(replacement + replacement + replacement + replacement) +"$2");
	}
}
