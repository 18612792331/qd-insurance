package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UserLoginRequest extends BaseRequest  {

	private static final long serialVersionUID = 5146878071254793768L;

	@ExplainAnnotation (explain = "手机号")
	@NotNullValidate
	private String mobile;

	@ExplainAnnotation (explain = "验证码")
	private String verifyCode;
	
	@ExplainAnnotation (explain = "密码")
	@NotNullValidate
	private String password;
	
	@ExplainAnnotation (explain = "来源类型")
	@NotNullValidate
	private Integer sourceType;


	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginRequest() {
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
