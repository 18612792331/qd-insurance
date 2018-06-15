package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class CheckPayPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	@NotNullValidate
	@WalletStatusValidate
	private String memberId;
	
	@NotNullValidate
	private String password;
	
	public CheckPayPasswordRequest() {

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

}
