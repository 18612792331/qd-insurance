package com.qding.api.call.app.qding.v1_3_1;

import java.util.HashMap;
import java.util.Map;

import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.struct.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.CheckPayPasswordRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.CheckPayPasswordRuleRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.CheckSecurityQuestionRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.ForgetPayPasswordRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.GetMySecurityQuestionsRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.GetSecurityQuestionsRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.SettingPayPasswordRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.request.UpdatePayPasswordRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.response.data.GetMySecurityQuestionsResponseData;
import com.qding.api.call.app.qding.v1_3_1.struct.wallet.response.data.GetSecurityQuestionsResponseData;
import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.sms.SendSms;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.MD5Util;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.useraccount.service.IRPasswordQuestionService;
import com.qding.useraccount.service.IRUserPayPasswordService;
import com.qding.useraccount.struct.request.PasswordQuestionByMemberRequest;
import com.qding.useraccount.struct.request.PasswordQuestionCheckRequest;
import com.qding.useraccount.struct.request.UserPayPwdCheckRequest;
import com.qding.useraccount.struct.request.UserPayPwdForgetPwdRequest;
import com.qding.useraccount.struct.request.UserPayPwdRequest;
import com.qding.useraccount.struct.request.UserPayPwdSaveRequest;
import com.qding.useraccount.struct.request.UserPayPwdUpdatePwdRequest;
import com.qding.useraccount.struct.response.PasswordQuestionCheckResponse;
import com.qding.useraccount.struct.response.PasswordQuestionListResponse;
import com.qding.useraccount.struct.response.UserPayPwdCheckResponse;
import com.qding.useraccount.struct.response.UserPayPwdResponse;
import com.qding.useraccount.struct.response.UserPayPwdSaveResponse;
import com.qding.useraccount.struct.response.UserPayPwdUpdateResponse;

/**
 * 我的钱包 1.3.1
 * @author lichao
 *
 */
public class CallWallet extends com.qding.api.call.app.qding.v1_3_0.CallWallet{

	@Autowired
	private IRUserPayPasswordService userPayPasswordService;
	
	@Autowired
	private IRPasswordQuestionService userPayPasswordQuestionService;
	
	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private SendSms sendSms;

	@Autowired
	private IProfileService profileAPI;

	private String getMobile(String memberId) throws ServiceException {
		
		GetMemberRequest request = new GetMemberRequest();
		
		request.setMemberId(memberId);
		
		GetMemberResponse response = profileService.getMemberById(request);
		
		checkAndContinue(response);
		
		return response.getMemberInfo().getMobile();
	}
	
	/**
	 * 设置支付密码
	 * @param request
	 * @return
	 */
	@HTTP(alias="settingPayPassword", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> settingPayPassword(SettingPayPasswordRequest request) {
		
		try {
			Response<ResponseData> response = new Response<>();
			
			if(!request.getPassword().equals(request.getConfirmPassword())) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "两次输入密码不一致");
			}
			
			UserPayPwdSaveRequest payPwdSaveRequest = transfor(UserPayPwdSaveRequest.class, request);

			UserPayPwdSaveResponse payPwdSaveResponse = userPayPasswordService.save(payPwdSaveRequest);
			
			checkAndContinue(payPwdSaveResponse);
			
			String mobile = getMobile(request.getMemberId());
			
			Map<String, String> data = new HashMap<>();
		
			data.put("mobile", sendSms.getSecretMobile(mobile, "*"));
			
			sendSms.send(
					SmsAction.SETTING_WALLET_PAYPASSPORT, mobile, data);
			
			response.setData(new ResponseData());
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
	}
	
	/**
	 * 修改支付密码
	 * @param request
	 * @return
	 */
	@HTTP(alias="updatePayPassword", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> updatePayPassword(UpdatePayPasswordRequest request) {
		
		try {
			Response<ResponseData> response = new Response<>();
			 
			if(!request.getNewPassword().equals(request.getConfirmNewPassword())) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "两次输入密码不一致");
			}
			
			UserPayPwdUpdatePwdRequest updatePwdRequest = transfor(UserPayPwdUpdatePwdRequest.class, request);
			
			UserPayPwdUpdateResponse updatePwdResponse = userPayPasswordService.updatePwd(updatePwdRequest);
			
			checkAndContinue(updatePwdResponse);
			
			String mobile = getMobile(request.getMemberId());
			
			Map<String, String> data = new HashMap<>();
		
			data.put("mobile", sendSms.getSecretMobile(mobile, "*"));
			
			sendSms.send(
					SmsAction.UPDATE_WALLET_PAYPASSPORT, mobile, data);
			
			response.setData(new ResponseData());

			return response;
		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
	}
	
	/**
	 * 获取密保问题
	 * @param request
	 * @return
	 */
	@HTTP(alias="getSecurityQuestions", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<GetSecurityQuestionsResponseData> getSecurityQuestions(GetSecurityQuestionsRequest request) {
		
		Response<GetSecurityQuestionsResponseData> response = new Response<>();
		
		PasswordQuestionListResponse getQuestionResponse = userPayPasswordQuestionService.list();
		
		GetSecurityQuestionsResponseData data = transfor(GetSecurityQuestionsResponseData.class, getQuestionResponse);
		
		response.setData(data);

		return response;
	}
	
	/**
	 * 获取我的密保问题
	 * @param request
	 * @return
	 */
	// TODO: 2016/3/14 ,临时取消密码提示信息token验证，2.0后期版本要追加回来 isRequireAuth=true, isNeedReLoginWhenExpire=true
	@HTTP(alias="getMySecurityQuestions")
	public Response<GetMySecurityQuestionsResponseData> getMySecurityQuestions(GetMySecurityQuestionsRequest request) {
		
		Response<GetMySecurityQuestionsResponseData> response = new Response<>();
		
		PasswordQuestionListResponse getQuestionResponse = userPayPasswordQuestionService.listByMemeber(
				transfor(PasswordQuestionByMemberRequest.class, request)
		);
		
		GetMySecurityQuestionsResponseData data = transfor(GetMySecurityQuestionsResponseData.class, getQuestionResponse);
		
		response.setData(data);
		
		return response;
	}

	/**
	 * 验证密保问题
	 * @param request
	 * @return
	 */
	@HTTP(alias="checkSecurityQuestion", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> checkSecurityQuestion(CheckSecurityQuestionRequest request) {
		
		try {
			Response<ResponseData> response = new Response<>();
			
			PasswordQuestionCheckRequest checkQuestionRequest = transfor(PasswordQuestionCheckRequest.class, request);
			
			PasswordQuestionCheckResponse checkQuestionResponse = userPayPasswordQuestionService.check(checkQuestionRequest);
			
			checkAndContinue(checkQuestionResponse);
			
			response.setData(new ResponseData());
		
			return response;
			
		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
		
	}
	
	/**
	 * 忘记支付密码
	 * @param request
	 * @return
	 */
	@HTTP(alias="forgetPayPassword", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> forgetPayPassword(ForgetPayPasswordRequest request) {
		
		try {
			Response<ResponseData> response = new Response<>();
			
			if(!request.getPassword().equals(request.getConfirmPassword())) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "两次输入密码不一致");
			}
			
			UserPayPwdResponse getPasswordResponse = userPayPasswordService.getByMember(
					new UserPayPwdRequest(request.getMemberId())
					);
			
			checkAndContinue(getPasswordResponse);
			
			String oldPassword = getPasswordResponse.getUserPayPasswordInfo().getPayPassword();
			
			if(oldPassword.equals(MD5Util.md5(request.getPassword()))) {
				
				throw new ServiceException(HttpStatus.CONFLICT.getStatusCode(), "您重置的新密码与旧密码相同");

			}
			
			UserPayPwdForgetPwdRequest forgetPasswordRequest = transfor(UserPayPwdForgetPwdRequest.class, request);
			
			UserPayPwdUpdateResponse forgetPasswordResponse = userPayPasswordService.forgetPwd(forgetPasswordRequest);
			
			checkAndContinue(forgetPasswordResponse);
			
			String mobile = getMobile(request.getMemberId());
			
			Map<String, String> data = new HashMap<>();
		
			data.put("mobile", sendSms.getSecretMobile(mobile, "*"));
			
			sendSms.send(SmsAction.FORGET_WALLET_PAYPASSPORT, mobile, data);
			
			response.setData(new ResponseData());
			
			return response;
		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
	}

	/**
	 * 验证支付密码规则
	 * @param request
	 * @return
	 */
	@HTTP(alias="checkPayPasswordRule", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> checkPayPasswordRule(CheckPayPasswordRuleRequest request) {
		
		try {
			
			WalletValidate.validateWallPayPasswordRule(request.getPassword());
			
			Response<ResponseData> response = new Response<>();
			
			response.setData(new ResponseData());
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
		
		
	}
	
	/**
	 * 验证支付密码
	 * @param request
	 * @return
	 */
	@HTTP(alias="checkPayPassword", isRequireAuth=true, isNeedReLoginWhenExpire=true)
	public Response<ResponseData> checkPayPassword(CheckPayPasswordRequest request) {
		
		try {

			Response<ResponseData> response = new Response<>();

			GetMemberRequest memberRequest = new GetMemberRequest();

			memberRequest.setMemberId(request.getMemberId());

			GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);

			if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {

				MemberInfo memberInfo = memberResponse.getMemberInfo();

				if (QDStringUtil.isNotNull(memberInfo.getStatus()) && memberInfo.getStatus() == 0) {

					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "支付者账户已被冻结,请选择其他支付方式");
				}
			}
			
			UserPayPwdCheckRequest pwdCheckRequest = transfor(UserPayPwdCheckRequest.class, request);

			UserPayPwdCheckResponse pwdCheckResponse = userPayPasswordService.check(pwdCheckRequest);
			
			try {

				checkAndContinue(pwdCheckResponse);
				
			} catch (ServiceException e) {
				
				WalletValidate.validateLeftTimes(request.getMemberId());
			}
			
			response.setData(new ResponseData());
			
			return response;

		} catch (Exception e) {
			
			return handleException(ResponseData.class, e);
		}
		
	}
	
}
