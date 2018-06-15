package com.qding.insurance.rpc.response;

import java.util.List;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.vo.InsurancePolicyInfo;

//用户保单列表
public class UserPolicyListResponse extends BaseResponse {

	private static final long serialVersionUID = -2248911865612924529L;
	private List<InsurancePolicyInfo> insurancePolicyInfoList;

	public List<InsurancePolicyInfo> getInsurancePolicyInfoList() {
		return insurancePolicyInfoList;
	}

	public void setInsurancePolicyInfoList(List<InsurancePolicyInfo> insurancePolicyInfoList) {
		this.insurancePolicyInfoList = insurancePolicyInfoList;
	}

}
