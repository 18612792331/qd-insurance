package com.qding.api.verifycode.send;

import java.util.HashMap;
import java.util.Map;

import com.qding.api.sms.SendSms;
import com.qding.api.sms.SmsAction;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

public class SmsSendCannel extends SendChannel{

	protected SmsAction action;
	
	public SmsSendCannel(String mobile, SmsAction action) {
		super.identity = mobile;
		this.action = action;
	}


	@Override
	public void sendVoicVerfyCode (String mobile,String code)  throws ServiceException {

		SendSms sendSms = ApplicationContextUtil.getBeansOfType(SendSms.class);
		boolean result = sendSms.sendVoiceVerfyCode(mobile,code);

		if(!result) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "发送失败");
		}
	}

	@Override
	public void send(String code) throws ServiceException{
		
		if(identity == null || action == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少参数");
		}
		
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("code", code);
		data.put("expire", "10");
		
		SendSms sendSms = ApplicationContextUtil.getBeansOfType(SendSms.class);
		
		String templateId = SmsAction.getSmsTemplateId(action.getAction());

		boolean result = sendSms.send(templateId, identity, data);
		
		if(!result) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "发送失败");
		}
	}

	@Override
	public void send(String code, String activityName) throws ServiceException {

		if(identity == null || action == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少参数");
		}

		Map<String, String> data = new HashMap<String, String>();

		data.put("number", code);
		data.put("activityName",activityName);

		SendSms sendSms = ApplicationContextUtil.getBeansOfType(SendSms.class);

		String templateId = SmsAction.getSmsTemplateId(action.getAction());

		boolean result = sendSms.send(templateId, identity, data);

		if(!result) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "发送失败");
		}
	}

}
