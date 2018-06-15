package com.qding.api.call.app.qding.v1_3_1.struct.wallet.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class CheckPayPasswordRuleRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611675613096850450L;

	private String password;
	
	public CheckPayPasswordRuleRequest() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
