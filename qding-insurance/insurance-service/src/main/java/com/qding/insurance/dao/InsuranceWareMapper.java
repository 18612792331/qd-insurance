package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.param.InsuranceWareParam;

public interface InsuranceWareMapper {
    
    int deleteByPrimaryKey(String id);

    int insert(InsuranceWare record);

    int insertSelective(InsuranceWare record);

    InsuranceWare selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InsuranceWare record);

    int updateByPrimaryKey(InsuranceWare record);
    
    int countByParam(InsuranceWareParam param);
    
    List<InsuranceWare> selectByParam(InsuranceWareParam param);
    
    InsuranceWareExtWithBLOBs selectBlobById(String id);

	List<InsuranceWareExtWithBLOBs> selectAllWareList();
}