package com.qding.api.call.common.struct.sms.request;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetActivityVerificationCodeIsEnableRequest extends BaseRequest {

	
	private static final long serialVersionUID = 2677298208779623848L;

	/**
     * 手机号
     */
	@NotNullValidate
	@ExplainAnnotation(explain = "手机号")
	private String mobile;
	
	/**
     * 验证码
     */
	@NotNullValidate
	@ExplainAnnotation(explain = "验证码")
	private String verifyCode;

	/**
	 *
	 */
	@NotNullValidate
	@ExplainAnnotation(explain = "活动的唯一标识",desc = "和发送验证码的标识保持一致")
	private String activityKey;

	@NotNullValidate
	@ExplainAnnotation(explain = "活动名称")
	private String activityName;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getActivityKey() {
		return activityKey;
	}

	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public GetActivityVerificationCodeIsEnableRequest(){
		
	}


}
