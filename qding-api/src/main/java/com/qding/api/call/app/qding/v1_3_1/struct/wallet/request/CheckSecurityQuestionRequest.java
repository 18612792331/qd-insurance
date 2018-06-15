package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_1.struct.wallet.CheckSecurityQuestion;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class CheckSecurityQuestionRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	@NotNullValidate
	@WalletStatusValidate
	private String memberId;
	
	@NotNullValidate
	private List<CheckSecurityQuestion> questions;
	
	public CheckSecurityQuestionRequest() {

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

}
