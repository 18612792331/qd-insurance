package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_1.struct.wallet.CheckSecurityQuestion;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.WalletPayPasswordRuleValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ForgetPayPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	@MemberValidate
	@WalletStatusValidate
	private String memberId;
	
	@NotNullValidate
	private List<CheckSecurityQuestion> questions;
	
	@WalletPayPasswordRuleValidate
	private String password;
	
	@NotNullValidate
	private String confirmPassword;
	
	public ForgetPayPasswordRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<CheckSecurityQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<CheckSecurityQuestion> questions) {
		this.questions = questions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
