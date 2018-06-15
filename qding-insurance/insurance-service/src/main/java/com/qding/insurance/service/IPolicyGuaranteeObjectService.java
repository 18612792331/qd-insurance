package com.qding.insurance.service;

import com.qding.insurance.domain.PolicyGuaranteeObject;

public interface IPolicyGuaranteeObjectService {

	/**
	 * 根据单证ID和商品ID 查保障对象
	 */

	PolicyGuaranteeObject getResultByPolicyIdAndObjectId(String policyId, String itemId);

}
