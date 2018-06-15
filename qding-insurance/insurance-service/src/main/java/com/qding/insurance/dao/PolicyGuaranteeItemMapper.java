package com.qding.insurance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qding.insurance.domain.PolicyGuaranteeItem;

public interface PolicyGuaranteeItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(PolicyGuaranteeItem record);

    int insertSelective(PolicyGuaranteeItem record);

    PolicyGuaranteeItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PolicyGuaranteeItem record);

    int updateByPrimaryKey(PolicyGuaranteeItem record);
    
    void batchInsert(List<PolicyGuaranteeItem> list);
    
    PolicyGuaranteeItem getByPolicyAndObject(@Param("policyId")String policyId, @Param("objId")String objId);

	/**
	 * 获取单证对应保障内容列表
	 */
	List<PolicyGuaranteeItem> getListByPolicyId(String policyId);
}