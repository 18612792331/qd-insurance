package com.qding.insurance.dao;

import com.qding.insurance.domain.SpecGroup;

public interface SpecGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecGroup record);

    int insertSelective(SpecGroup record);

    SpecGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecGroup record);

    int updateByPrimaryKey(SpecGroup record);
}