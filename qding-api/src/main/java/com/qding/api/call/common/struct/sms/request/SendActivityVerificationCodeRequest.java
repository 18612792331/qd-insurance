package com.qding.api.call.common.struct.sms.request;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SendActivityVerificationCodeRequest extends BaseRequest {


	private static final long serialVersionUID = 1530889334040941123L;

	/**
     * 手机号
     */
	@NotNullValidate
	@ExplainAnnotation(explain = "手机号")
	private String mobile;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "活动的唯一标识",desc = "用于区分其他活动，建议采用活动全拼或缩写")
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

	public SendActivityVerificationCodeRequest(){}

	@Override
	public String toString() {
		return "SendActivityVerificationCodeRequest{" +
				"mobile='" + mobile + '\'' +
				", activityKey='" + activityKey + '\'' +
				", activityName='" + activityName + '\'' +
				'}';
	}
}
