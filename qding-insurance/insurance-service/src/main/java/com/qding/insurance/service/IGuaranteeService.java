package com.qding.insurance.service;

import java.util.List;

import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.domain.PolicyGuaranteeObject;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.vo.GuaranteeItemVo;

public interface IGuaranteeService {
    
    GuaranteeItem getItemById(String id);
    
    void addGuarantee(List<GuaranteeItemVo> list);
    
    void addGuaranteePlan(List<GuaranteePlan> list);
    
    List<GuaranteeItemVo> getWareGuarantee(String wareId);
    
    List<GuaranteePlan> getWarePlan(String wareId);
    
    List<GuaranteePlan> getWarePlan(String wareId, String skuId);
    
    void batchAddGuarantee(List<PolicyGuaranteeItem> itemList, List<PolicyGuaranteeObject> objectList, List<PolicyGuaranteePlan> planList);
}
  
