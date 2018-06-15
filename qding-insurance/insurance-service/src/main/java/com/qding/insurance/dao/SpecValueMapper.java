package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.SpecValue;
import com.qding.insurance.vo.SpecValueVo;

public interface SpecValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecValue record);

    int insertSelective(SpecValue record);

    SpecValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecValue record);

    int updateByPrimaryKey(SpecValue record);
    
    List<SpecValueVo> selectAll();
}