package com.qding.insurance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.vo.PolicyPlanVo;

public interface PolicyGuaranteePlanMapper {
    int deleteByPrimaryKey(String id);

    int insert(PolicyGuaranteePlan record);

    int insertSelective(PolicyGuaranteePlan record);

    PolicyGuaranteePlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PolicyGuaranteePlan record);

    int updateByPrimaryKey(PolicyGuaranteePlan record);
    
    void batchInsert(List<PolicyGuaranteePlan> list);

    /**
     * 根据保障内容ID和单证查 单证保障权益
     */
    PolicyGuaranteePlan selectByguaranteeItemId(@Param("guaranteeItemId")String guaranteeItemId, @Param("policyId")String policyId);
    
    PolicyGuaranteePlan selectForUpdate(@Param("guaranteeItemId")String guaranteeItemId, @Param("policyId")String policyId);

	List<PolicyPlanVo> getPolicyPlan(String policyID);

	
 
}