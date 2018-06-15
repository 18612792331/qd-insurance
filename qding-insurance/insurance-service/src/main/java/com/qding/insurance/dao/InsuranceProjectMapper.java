package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.InsuranceProject;

public interface InsuranceProjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(InsuranceProject record);

    int insertSelective(InsuranceProject record);

    InsuranceProject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InsuranceProject record);

    int updateByPrimaryKey(InsuranceProject record);
    
    void batchInsert(List<InsuranceProject> list);
    
    void deleteByWareId(String wareId);
    
    List<InsuranceProject> selectByWareId(String wareId);
}