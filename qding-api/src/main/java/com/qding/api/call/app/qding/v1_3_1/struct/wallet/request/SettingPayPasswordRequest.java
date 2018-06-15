package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_1.struct.wallet.SettingSecurityQuestion;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.WalletPayPasswordRuleValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SettingPayPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	@MemberValidate
	@WalletStatusValidate
	private String memberId;
	
	@WalletPayPasswordRuleValidate
	private String password;
	
	@NotNullValidate
	private String confirmPassword;
	
	@NotNullValidate
	private List<SettingSecurityQuestion> questions;
	
	public SettingPayPasswordRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SettingSecurityQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SettingSecurityQuestion> questions) {
		this.questions = questions;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
