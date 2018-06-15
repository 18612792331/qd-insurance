package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class InsurancePolicyDetailRequest extends BaseRequest{

	private static final long serialVersionUID = -7427649675204698921L;

	//保险单证ID（非PICC订单号）
	private String policyId;

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
}
