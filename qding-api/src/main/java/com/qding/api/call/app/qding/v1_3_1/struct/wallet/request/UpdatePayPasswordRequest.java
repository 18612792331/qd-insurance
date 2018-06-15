package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.WalletPayPasswordRuleValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UpdatePayPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	@MemberValidate
	@WalletStatusValidate
	private String memberId;
	
	@NotNullValidate
	private String oldPassword;
	
	@WalletPayPasswordRuleValidate
	private String newPassword;
	
	@NotNullValidate
	private String confirmNewPassword;
	
	public UpdatePayPasswordRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
