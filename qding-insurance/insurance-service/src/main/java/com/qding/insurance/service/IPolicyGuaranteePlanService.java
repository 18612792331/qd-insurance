package com.qding.insurance.service;

import com.qding.insurance.domain.PolicyGuaranteePlan;

public interface IPolicyGuaranteePlanService {
    
    /**
     * 锁定权益
     */
    void lockRight(String policyId, String itemId, String value) throws Exception;
    
    /**
     * 解锁权益，释放锁定权益
     */
    void unLockRight(String policyId, String itemId, String value) throws Exception;
    
    /**
     * 完成锁定权益
     */
    void finishLockRight(String policyId, String itemId, String value) throws Exception;

	/**
	 * 根据单证ID和保障内容ID查剩余保障权益
	 */
	PolicyGuaranteePlan getpolicyGuaranteePlanByPolicyIDAndItemID(String policyID,String itemID);


}
