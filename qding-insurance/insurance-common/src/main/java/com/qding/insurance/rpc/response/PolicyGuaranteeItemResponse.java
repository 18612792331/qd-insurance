package com.qding.insurance.rpc.response;

import java.util.List;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.domain.PolicyGuaranteeItem;

public class PolicyGuaranteeItemResponse extends BaseResponse {

	private static final long serialVersionUID = 7687031258128495193L;

	private List<PolicyGuaranteeItem> policyGuaranteeItemList;

	public List<PolicyGuaranteeItem> getPolicyGuaranteeItemList() {
		return policyGuaranteeItemList;
	}

	public void setPolicyGuaranteeItemList(List<PolicyGuaranteeItem> policyGuaranteeItemList) {
		this.policyGuaranteeItemList = policyGuaranteeItemList;
	}

}
