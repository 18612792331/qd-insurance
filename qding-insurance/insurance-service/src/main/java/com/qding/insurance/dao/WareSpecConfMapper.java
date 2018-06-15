package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.WareSpecConf;

public interface WareSpecConfMapper {
    int deleteByPrimaryKey(String id);

    int insert(WareSpecConf record);

    int insertSelective(WareSpecConf record);

    WareSpecConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WareSpecConf record);

    int updateByPrimaryKey(WareSpecConf record);
    
    void deleteByWareId(String wareId);
    
    void batchInsert(List<WareSpecConf> list);
    
    List<WareSpecConf> selectByWareId(String wareId);
}