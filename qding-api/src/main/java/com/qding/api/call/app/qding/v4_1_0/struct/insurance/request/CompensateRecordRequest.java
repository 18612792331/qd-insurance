package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class CompensateRecordRequest extends BaseRequest{
	private static final long serialVersionUID = -1752771623966163563L;
    
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	private String memberId;
	private String policyId;
	private String status;
}
