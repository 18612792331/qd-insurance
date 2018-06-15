package com.qding.insurance.dao;

import java.util.HashMap;
import java.util.List;

import com.qding.insurance.domain.InsuranceSku;

public interface InsuranceSkuMapper {
    int deleteByPrimaryKey(String id);

    int insert(InsuranceSku record);

    int insertSelective(InsuranceSku record);

    InsuranceSku selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InsuranceSku record);

    int updateByPrimaryKey(InsuranceSku record);
    
    void deleteByWareId(String wareId);
    
    void batchInsert(List<InsuranceSku> list);
    
    List<InsuranceSku> selectByWareId(String wareId);

	List<InsuranceSku> selectBySkuTypes(HashMap<String, Object> params);
	
	InsuranceSku getByBrickSkuId(String brickSkuId);
}