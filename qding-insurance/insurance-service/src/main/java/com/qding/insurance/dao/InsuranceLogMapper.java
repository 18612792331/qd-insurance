package com.qding.insurance.dao;

import com.qding.insurance.domain.InsuranceLog;

public interface InsuranceLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(InsuranceLog record);

    int insertSelective(InsuranceLog record);

    InsuranceLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InsuranceLog record);

    int updateByPrimaryKey(InsuranceLog record);
}