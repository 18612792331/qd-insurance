package com.qding.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.PolicyGuaranteeItemMapper;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.service.IPolicyGuaranteeItemService;
@Service("policyGuaranteeItemService")
public class PolicyGuaranteeItemServiceImpl implements IPolicyGuaranteeItemService {
	
	@Autowired
	private PolicyGuaranteeItemMapper policyGuaranteeItemMapper;
	@Override
	public PolicyGuaranteeItem getResultByID(String guaranteeItemId) {
		return policyGuaranteeItemMapper.selectByPrimaryKey(guaranteeItemId);

	}
    @Override
    public PolicyGuaranteeItem getByPolicyAndObject(String policyId, String objId) {
        return policyGuaranteeItemMapper.getByPolicyAndObject(policyId, objId);
    }
	@Override
	public List<PolicyGuaranteeItem> getByPolicyId(String policyId) {
		
		List<PolicyGuaranteeItem> list = policyGuaranteeItemMapper.getListByPolicyId(policyId);
		
		return list;
	}

}
