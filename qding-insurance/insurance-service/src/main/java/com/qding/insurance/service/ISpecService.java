package com.qding.insurance.service;

import java.util.List;

import com.qding.insurance.domain.WareSpecConf;
import com.qding.insurance.vo.SpecVo;

public interface ISpecService {
    
    List<SpecVo> getAllSpec();
    
    List<SpecVo> getAllSpecWithSelected(String wareId);
    
    void addWareSpecConf(List<WareSpecConf> list);
}
  
