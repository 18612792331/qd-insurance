package com.qding.api.call.common;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.common.struct.sms.request.GetActivityVerificationCodeIsEnableRequest;
import com.qding.api.call.common.struct.sms.request.SendActivityVerificationCodeRequest;
import com.qding.api.call.common.struct.sms.response.data.GetActivityVerificationCodeIsEnableResponseData;
import com.qding.api.call.common.struct.sms.response.data.SendActivityVerificationCodeResponseData;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.verifycode.ActivityVerifyCode;
import com.qding.api.verifycode.SendCodeConfig;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.generator.DefaultGeneratorCode;
import com.qding.api.verifycode.send.SmsSendCannel;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

public class CallActivitySms extends Callable{


	/**
	 * 发送短信验证码
	 * @param request
	 * @return
	 */
	@HTTP(alias = "sendActivityVerificationCode")
	public Response<SendActivityVerificationCodeResponseData> sendActivityVerificationCode (SendActivityVerificationCodeRequest request) {

		String mobile = request.getMobile();
		try {
			SmsAction smsAction = SmsAction.to(0);
			ActivityVerifyCode.sendCode(
					new SendCodeConfig(
							System.currentTimeMillis() + 100 * 1000,
							smsAction,
							new DefaultGeneratorCode(),
							new RedisStoreDevice(),
							new SmsSendCannel(mobile, smsAction),
							10 * 60,
							request.getActivityKey(),
							request.getActivityName(),
							1
					)
			);

			Response<SendActivityVerificationCodeResponseData> response = new Response<SendActivityVerificationCodeResponseData>();

			response.setData(new SendActivityVerificationCodeResponseData());

			return response;

		} catch (Exception e) {

			return handleException(SendActivityVerificationCodeResponseData.class, e);
		}

	}


	/*
	 * 验证码是否有效
	 * @param request
	 * @return
	 */
	@HTTP(alias = "checkActivityVerificationCode")
	public Response<GetActivityVerificationCodeIsEnableResponseData> checkActivityVerificationCode (GetActivityVerificationCodeIsEnableRequest request) {

		String mobile = request.getMobile();

		String code = request.getVerifyCode();

		try {
			SmsAction smsAction = SmsAction.to(0);

			if(smsAction == null) {

				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的action");

			}

			ActivityVerifyCode.verifyCode(
					new VerifyCodeConfig(
							smsAction,
							code,
							mobile,
							new RedisStoreDevice(),
							request.getActivityKey(),
							request.getActivityName()
					)
			);

			Response<GetActivityVerificationCodeIsEnableResponseData> response = new Response<GetActivityVerificationCodeIsEnableResponseData>();

			response.setData(new GetActivityVerificationCodeIsEnableResponseData());

			return response;

		} catch (Exception e) {

			return handleException(GetActivityVerificationCodeIsEnableResponseData.class, e);
		}

	}
}
