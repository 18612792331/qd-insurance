package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AuthorizeRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421970255152237096L;

	@NotNullValidate
	private String accountId;
	
	public AuthorizeRequest() {

	}

	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
