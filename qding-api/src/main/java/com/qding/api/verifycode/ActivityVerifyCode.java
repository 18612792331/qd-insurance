package com.qding.api.verifycode;

import com.qding.api.sms.SmsAction;
import com.qding.api.verifycode.send.SendChannel;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import org.apache.log4j.Logger;


public class ActivityVerifyCode {

	private static  Logger logger = Logger.getLogger(ActivityVerifyCode.class);

	public static void sendCode(SendCodeConfig config) throws ServiceException {

		StoreDevice store = config.getStore();

		long expireAt = config.getExpireAt();

		SendChannel sendChannel = config.getSendChannel();

		SmsAction action = config.getAction();

		String codeKey = "store:verify:code:" + sendChannel.getIdentity() + ":" + config.getActivityKey();

		String code = store.getCode(codeKey);

		if ( code == null ){

			 code = config.getGeneratorCode().getCode();

			 store.setCode(codeKey,code,config.getCodeExpireAt());
		}

		if(code == null || expireAt < System.currentTimeMillis() || store == null || sendChannel == null || action == null) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
		}

		String key = "verify:code:" + sendChannel.getIdentity() + ":" + config.getActivityKey();

		StoreCode verifyCode = store.get(key);

		logger.info("验证码============="+code);

		if(verifyCode == null) {

			sendChannel.send(code,config.getActivityName());

			store.set(key, new StoreCode(code, expireAt), expireAt);
		}
		else {

			long left = (verifyCode.getExpireAt() - System.currentTimeMillis()) / 1000;

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请" + left + "秒后重新发送");
		}
	}

	public static boolean verifyCode(VerifyCodeConfig config) throws ServiceException {
		
		StoreDevice store = config.getStore();
		
		String identity = config.getIdentity();
		
		String code = config.getCode();
		
		SmsAction action = config.getAction();
		
		if(identity == null || code == null || store == null || action == null) {
		
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
		}
		
		String key = "verify:code:" + identity + ":" + config.getActivityKey();
		
		StoreCode verifyCode = store.get(key);
		
		if(verifyCode == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码已过期，请重新发送");
		}
		else {
			
			if(!verifyCode.getCode().equals(code)) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码错误");
			}
		}
		
		return true;
	}
}
