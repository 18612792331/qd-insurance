package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.InsuranceWareExt;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;

public interface InsuranceWareExtMapper {
    int deleteByPrimaryKey(String id);

    int insert(InsuranceWareExtWithBLOBs record);

    int insertSelective(InsuranceWareExtWithBLOBs record);

    InsuranceWareExtWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InsuranceWareExtWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InsuranceWareExtWithBLOBs record);

    int updateByPrimaryKey(InsuranceWareExt record);

	List<InsuranceWareExtWithBLOBs> selectByWareId(String wareId);
}