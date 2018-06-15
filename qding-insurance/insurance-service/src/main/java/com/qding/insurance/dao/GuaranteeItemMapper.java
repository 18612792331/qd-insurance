package com.qding.insurance.dao;

import java.util.List;

import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.vo.GuaranteeItemVo;

public interface GuaranteeItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(GuaranteeItem record);

    int insertSelective(GuaranteeItem record);

    GuaranteeItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GuaranteeItem record);

    int updateByPrimaryKey(GuaranteeItem record);
    
    void deleteByWareId(String wareId);
    
    void batchInsert(List<GuaranteeItemVo> list);
    
    List<GuaranteeItemVo> selectByWareId(String wareId);
    
    GuaranteeItemVo selectVoById(String id);
}