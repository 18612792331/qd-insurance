package com.qding.api.sms;

import java.util.HashMap;
import java.util.Map;

import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

public enum SmsAction {

	ACTIVITY(0),
	REGISTER(1),
	FORGET_PWD(2),
	BIND_MOBILE(3),
	BIND_ROOM(4),
	UPDATE_MOBILE(5),
	LOGIN(7),
	OPEN_PROPERTY_FEE_PAY_VERIFY(8),

	SETTING_WALLET_PAYPASSPORT(100),
	UPDATE_WALLET_PAYPASSPORT(101),
	FORGET_WALLET_PAYPASSPORT(102),
	OPEN_PROPERTY_FEE_PAY(103);
	
	private int action;
	
	SmsAction(int action) {
		this.action = action;
	}
	
	public static SmsAction to(int action) {
		SmsAction[] values = SmsAction.values();
		for(SmsAction s : values) {
			if(s.getAction() == action) {
				return s;
			}
		}
		return null;
	}
	
	public static final Map<SmsAction, String> actionForSmsTemplateIdMap = new HashMap<SmsAction, String>();
	
	static {
		
		actionForSmsTemplateIdMap.put(SmsAction.REGISTER, "64");
		actionForSmsTemplateIdMap.put(SmsAction.FORGET_PWD, "65");
		actionForSmsTemplateIdMap.put(SmsAction.BIND_MOBILE, "66");
		actionForSmsTemplateIdMap.put(SmsAction.BIND_ROOM, "67");
		actionForSmsTemplateIdMap.put(SmsAction.UPDATE_MOBILE, "68");
		actionForSmsTemplateIdMap.put(SmsAction.LOGIN, "loginVerifyCode");
		actionForSmsTemplateIdMap.put(SmsAction.OPEN_PROPERTY_FEE_PAY_VERIFY, "wuyefeiverifycode001");
		
		
		actionForSmsTemplateIdMap.put(SmsAction.SETTING_WALLET_PAYPASSPORT, "1149");
		actionForSmsTemplateIdMap.put(SmsAction.UPDATE_WALLET_PAYPASSPORT, "1150");
		actionForSmsTemplateIdMap.put(SmsAction.FORGET_WALLET_PAYPASSPORT, "1151");
		actionForSmsTemplateIdMap.put(SmsAction.ACTIVITY, "canjiabaoming001");
		actionForSmsTemplateIdMap.put(SmsAction.OPEN_PROPERTY_FEE_PAY, "wuyefeiNotify001");
		
		
	}
	
	public static String getSmsTemplateId(int action) throws ServiceException {
		
		SmsAction smsAction = SmsAction.to(action);
		
		if(smsAction == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的action");
		}
		return actionForSmsTemplateIdMap.get(smsAction);
		
	}
	
	public int getAction() {
		return action;
	}
	
}
