package com.qding.insurance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qding.insurance.domain.PolicyGuaranteeObject;

public interface PolicyGuaranteeObjectMapper {
	int deleteByPrimaryKey(String id);

	int insert(PolicyGuaranteeObject record);

	int insertSelective(PolicyGuaranteeObject record);

	PolicyGuaranteeObject selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(PolicyGuaranteeObject record);

	int updateByPrimaryKey(PolicyGuaranteeObject record);
	
	void batchInsert(List<PolicyGuaranteeObject> list);

	/**
	 * 根据单证ID和商品ID 查保障对象
	 * 
	 */
	PolicyGuaranteeObject getResultByPolicyIdAndObjectId(@Param("policyId")String policyId,@Param("itemId")String itemId);
}