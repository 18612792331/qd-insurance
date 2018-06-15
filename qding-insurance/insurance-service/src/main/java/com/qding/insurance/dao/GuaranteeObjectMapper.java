package com.qding.insurance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qding.insurance.domain.GuaranteeObject;

public interface GuaranteeObjectMapper {
    
    void deleteByWareId(String wareId);
    
    void batchInsert(List<GuaranteeObject> list); 
    
    List<GuaranteeObject> selectByWareAndItem(@Param("wareId")String wareId, @Param("itemId")String itemId);
}