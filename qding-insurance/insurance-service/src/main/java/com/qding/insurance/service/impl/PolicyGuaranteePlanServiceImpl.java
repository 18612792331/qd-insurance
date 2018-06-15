package com.qding.insurance.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.PolicyGuaranteePlanMapper;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.service.IPolicyGuaranteePlanService;
import com.qding.insurance.util.Constant;
@Service("policyGuaranteePlanService")
public class PolicyGuaranteePlanServiceImpl implements IPolicyGuaranteePlanService {

	@Autowired
	private PolicyGuaranteePlanMapper policyGuaranteePlanMapper;
	

	@Override
	public PolicyGuaranteePlan getpolicyGuaranteePlanByPolicyIDAndItemID(String policyID, String itemID) {
		return policyGuaranteePlanMapper.selectByguaranteeItemId(itemID,policyID);
	}

    @Override
    public void lockRight(String policyId, String itemId, String value)  throws Exception {
        
        PolicyGuaranteePlan plan = policyGuaranteePlanMapper.selectForUpdate(itemId, policyId);
        
        if(plan == null){
            throw new Exception("保单权益不存在");
        }
        
        // 非无限权益才需要操作
        if(!plan.getRightValue().equals(Constant.GUARANTEE_PLAN_UNLIMIT)){
            BigDecimal toLockValue = new BigDecimal(value);
            BigDecimal balance = new BigDecimal(plan.getBalanceValue());
            BigDecimal lockedValue = new BigDecimal(plan.getLockValue());
            
            if(balance.compareTo(toLockValue) == -1){
                throw new Exception("保单权益额度不足");
            }
            
            plan.setBalanceValue(balance.subtract(toLockValue).toString());
            plan.setLockValue(lockedValue.add(toLockValue).toString());
        }
        
        policyGuaranteePlanMapper.updateByPrimaryKeySelective(plan);
    }

    @Override
    public void unLockRight(String policyId, String itemId, String value) throws Exception{
        PolicyGuaranteePlan plan = policyGuaranteePlanMapper.selectForUpdate(itemId, policyId);
        
        if(plan == null){
            throw new Exception("保单权益不存在");
        }
        
        // 非无限权益才需要操作
        if(!plan.getRightValue().equals(Constant.GUARANTEE_PLAN_UNLIMIT)){
            BigDecimal unLockValue = new BigDecimal(value);
            BigDecimal balance = new BigDecimal(plan.getBalanceValue());
            BigDecimal lockedValue = new BigDecimal(plan.getLockValue());
            
            if(lockedValue.compareTo(unLockValue) == -1){
                throw new Exception("保单锁定权益额度不足");
            }
            
            plan.setBalanceValue(balance.add(unLockValue).toString());
            plan.setLockValue(lockedValue.subtract(unLockValue).toString());
        }
        
        policyGuaranteePlanMapper.updateByPrimaryKeySelective(plan);
    }

    @Override
    public void finishLockRight(String policyId, String itemId, String value) throws Exception {
        PolicyGuaranteePlan plan = policyGuaranteePlanMapper.selectForUpdate(itemId, policyId);
        
        if(plan == null){
            throw new Exception("保单权益不存在");
        }
        
        // 非无限权益才需要操作
        if(!plan.getRightValue().equals(Constant.GUARANTEE_PLAN_UNLIMIT)){
            BigDecimal toFinishValue = new BigDecimal(value);
            BigDecimal paidValue = new BigDecimal(plan.getPaidValue());
            BigDecimal lockedValue = new BigDecimal(plan.getLockValue());
            
            if(lockedValue.compareTo(toFinishValue) == -1){
                throw new Exception("保单锁定权益额度不足");
            }
            
            plan.setPaidValue(paidValue.add(toFinishValue).toString());
            plan.setLockValue(lockedValue.subtract(toFinishValue).toString());
        }
        
        policyGuaranteePlanMapper.updateByPrimaryKeySelective(plan);
    }


}
