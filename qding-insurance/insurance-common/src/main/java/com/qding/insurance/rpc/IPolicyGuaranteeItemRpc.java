package com.qding.insurance.rpc;

import com.qding.insurance.rpc.response.PolicyGuaranteeItemResponse;

public interface IPolicyGuaranteeItemRpc {

	PolicyGuaranteeItemResponse getPolicyGuaranteeItemList(String policyId);
}
