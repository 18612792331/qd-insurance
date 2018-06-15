package com.qding.insurance.service;

import java.util.List;

import com.qding.insurance.domain.PolicyGuaranteeItem;

public interface IPolicyGuaranteeItemService {

	PolicyGuaranteeItem getResultByID(String guaranteeItemId);
	
	PolicyGuaranteeItem getByPolicyAndObject(String policyId, String objId);

	
	/**
	 * 获取单证对应保障内容列表
	 */
	List<PolicyGuaranteeItem> getByPolicyId(String policyId);

}
