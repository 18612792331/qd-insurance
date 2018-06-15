package com.qding.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.PolicyGuaranteeObjectMapper;
import com.qding.insurance.domain.PolicyGuaranteeObject;
import com.qding.insurance.service.IPolicyGuaranteeObjectService;
@Service("policyGuaranteeObjectService")
public class PolicyGuaranteeObjectServiceImpl implements IPolicyGuaranteeObjectService {
	@Autowired
	private PolicyGuaranteeObjectMapper policyGuaranteeObjectMapper;

	@Override
	public PolicyGuaranteeObject getResultByPolicyIdAndObjectId(String policyId,String itemId) {
		return policyGuaranteeObjectMapper.getResultByPolicyIdAndObjectId(policyId, itemId);
	}

}
