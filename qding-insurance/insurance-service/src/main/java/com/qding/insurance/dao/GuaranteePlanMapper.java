package com.qding.insurance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.vo.GuaranteePlanVo;

public interface GuaranteePlanMapper {
    
    void deleteByWareId(String wareId);
    
    void batchInsert(List<GuaranteePlan> list);
    
    List<GuaranteePlan> selectByWareAndSku(@Param("wareId")String wareId, @Param("skuId")String skuId);

    List<GuaranteePlanVo> selectBySkuId(String skuId);
}