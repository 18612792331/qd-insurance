package com.qding.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.SpecValueMapper;
import com.qding.insurance.dao.WareSpecConfMapper;
import com.qding.insurance.domain.WareSpecConf;
import com.qding.insurance.service.ISpecService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.SpecValueVo;
import com.qding.insurance.vo.SpecVo;

@Service("specService")
public class SpecServiceImpl implements ISpecService{
    
    @Autowired
    private SpecValueMapper valueMapper;
    
    @Autowired
    private WareSpecConfMapper wareSpecConfMapper;

    @Override
    public List<SpecVo> getAllSpec() {
        
        List<SpecVo> ret = new ArrayList<SpecVo>();
        // 所有规格，含group信息
        List<SpecValueVo> list = valueMapper.selectAll();
        
        // 平面集合按group重组为二层
        String groupCode = null;
        SpecVo vo = null;
        List<SpecVo> children = null;
        
        for(SpecValueVo value : list){
            if(!value.getGroupCode().equals(groupCode)){
                groupCode = value.getGroupCode();
                vo = new SpecVo(value.getGroupCode(), value.getGroupName());
                children = findChildren(groupCode, list);
                vo.setChildren(children);
                ret.add(vo);
            }
        }
        return ret;
    }
    
    
    private List<SpecVo> findChildren(String groupCode, List<SpecValueVo> sourceList){
        
        List<SpecVo> children = new ArrayList<SpecVo>();
        
        for(SpecValueVo value : sourceList){
            if(value.getGroupCode().equals(groupCode)){
                children.add(new SpecVo(value.getValueCode(), value.getValueName()));
            }
        }
        
        return children;
    }


    @Override
    public void addWareSpecConf(List<WareSpecConf> list) {
        wareSpecConfMapper.deleteByWareId(list.get(0).getWareId());
        wareSpecConfMapper.batchInsert(list);
    }


    @Override
    public List<SpecVo> getAllSpecWithSelected(String wareId) {
        
        List<SpecVo> allSpecList = getAllSpec();
        
        List<WareSpecConf> wareSpec = wareSpecConfMapper.selectByWareId(wareId);
        
        for(SpecVo vo : allSpecList){
            if(CollectionUtils.isNotEmpty(vo.getChildren())){
                for(SpecVo child : vo.getChildren()){
                    for(WareSpecConf wareSpecConf : wareSpec){
                        if(child.getCode().equals(wareSpecConf.getSpecValueCode())){
                            child.setIsSelected(Constant.YES);
                            break;
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    



    
    
}
  
