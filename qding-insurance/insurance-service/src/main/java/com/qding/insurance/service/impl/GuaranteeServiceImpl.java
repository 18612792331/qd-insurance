package com.qding.insurance.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.GuaranteeItemMapper;
import com.qding.insurance.dao.GuaranteeObjectMapper;
import com.qding.insurance.dao.GuaranteePlanMapper;
import com.qding.insurance.dao.PolicyGuaranteeItemMapper;
import com.qding.insurance.dao.PolicyGuaranteeObjectMapper;
import com.qding.insurance.dao.PolicyGuaranteePlanMapper;
import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.domain.GuaranteeObject;
import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.domain.PolicyGuaranteeObject;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.service.IGuaranteeService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.GuaranteeItemVo;

@Service("guaranteeService")
public class GuaranteeServiceImpl implements IGuaranteeService{
    
    @Autowired
    private GuaranteeItemMapper itemMapper;

    @Autowired
    private GuaranteeObjectMapper objectMapper;
    
    @Autowired
    private GuaranteePlanMapper planMapper;
    
    @Autowired
    private PolicyGuaranteeItemMapper policyItemMapper;
    
    @Autowired
    private PolicyGuaranteeObjectMapper policyObjectMapper;
    
    @Autowired
    private PolicyGuaranteePlanMapper policyPlanMapper;
    
    @Override
    public void addGuarantee(List<GuaranteeItemVo> list) {
        
        String wareId = list.get(0).getWareId();
        
        // 保障内容
        itemMapper.deleteByWareId(wareId);
        itemMapper.batchInsert(list);
        
        // 保障对象
        objectMapper.deleteByWareId(wareId);
        
        for(GuaranteeItemVo item : list){ 
            if(CollectionUtils.isNotEmpty(item.getGuaranteeObjectList())){
                for(GuaranteeObject object : item.getGuaranteeObjectList()){
                    object.setId(Constant.ID_GENERATOR.generate());
                    object.setItemId(item.getId());
                    object.setWareId(item.getWareId());
                }
                objectMapper.batchInsert(item.getGuaranteeObjectList());
            }
        }
    }

    @Override
    public void addGuaranteePlan(List<GuaranteePlan> list) {
        planMapper.deleteByWareId(list.get(0).getWareId());
        planMapper.batchInsert(list);
    }

    @Override
    public List<GuaranteeItemVo> getWareGuarantee(String wareId) {
        
        List<GuaranteeItemVo> list = itemMapper.selectByWareId(wareId);
        List<GuaranteeObject> objList = null;
        for(GuaranteeItemVo vo : list){
            objList = objectMapper.selectByWareAndItem(wareId, vo.getId());
            vo.setGuaranteeObjectList(objList);
        }
        
        return list;
    }

    @Override
    public List<GuaranteePlan> getWarePlan(String wareId) {
        return planMapper.selectByWareAndSku(wareId, null);
    }

    @Override
    public List<GuaranteePlan> getWarePlan(String wareId, String skuId) {
        return planMapper.selectByWareAndSku(wareId, skuId);
    }

    @Override
    public void batchAddGuarantee(List<PolicyGuaranteeItem> itemList, List<PolicyGuaranteeObject> objectList,
            List<PolicyGuaranteePlan> planList) {
        
        if(CollectionUtils.isNotEmpty(itemList)){
            policyItemMapper.batchInsert(itemList);
        }
        if(CollectionUtils.isNotEmpty(objectList)){
            policyObjectMapper.batchInsert(objectList);
        }
        if(CollectionUtils.isNotEmpty(planList)){
            policyPlanMapper.batchInsert(planList);
        }
        
    }

    @Override
    public GuaranteeItem getItemById(String id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    
    



    
    
}
  
