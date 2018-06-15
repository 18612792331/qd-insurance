package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class InsurancePolicyListRequest extends BaseRequest{

	private static final long serialVersionUID = 5441821433118698538L;
   
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
