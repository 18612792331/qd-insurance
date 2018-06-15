package com.qding.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.dao.InsuranceProjectMapper;
import com.qding.insurance.dao.InsuranceSkuMapper;
import com.qding.insurance.dao.InsuranceWareExtMapper;
import com.qding.insurance.dao.InsuranceWareMapper;
import com.qding.insurance.domain.InsuranceProject;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.param.InsuranceWareParam;
import com.qding.insurance.param.SaveWareParam;
import com.qding.insurance.service.BrickWareTransService;
import com.qding.insurance.service.IInsuranceWareService;
import com.qding.insurance.util.Constant;

@Service("insuranceWareService")
public class InsuranceWareServiceImpl implements IInsuranceWareService{
    
    @Autowired
    private InsuranceWareMapper insuranceWareMapper;
    
    @Autowired
    private InsuranceWareExtMapper extMapper;
    
    @Autowired
    private InsuranceProjectMapper projectMapper;
    
    @Autowired
    private BrickWareTransService brickWareTransService;
    
    @Autowired
    private InsuranceSkuMapper skuMapper;
    

    @Override
    public ResultPage<InsuranceWare> getResultPage(InsuranceWareParam param) {
        ResultPage<InsuranceWare> resultPage = null;
        // 查询总数
        Integer totalCount = insuranceWareMapper.countByParam(param);
        // 查询列表
        List<InsuranceWare> list = insuranceWareMapper.selectByParam(param);
        
        resultPage = new ResultPage<InsuranceWare>(totalCount, param.getSize(),
               param.getPage(), list);
        
        return resultPage;
    }


    @Override
    public void add(SaveWareParam param) {
        insuranceWareMapper.insertSelective(param);
        extMapper.insertSelective(param);
        
        batchProject(param.getId(), param.getProjectList(), true);
    }


    @Override
    public void update(SaveWareParam param) {
        insuranceWareMapper.updateByPrimaryKeySelective(param);
        extMapper.updateByPrimaryKeyWithBLOBs(param);
        
        batchProject(param.getId(), param.getProjectList(), false);
    }
    
    private void batchProject(String wareId, List<InsuranceProject> list, boolean isAdd){
        
        if(CollectionUtils.isNotEmpty(list)){
            for(InsuranceProject project : list){
                project.setId(Constant.ID_GENERATOR.generate());
                project.setWareId(wareId);
            }
            
            if(!isAdd){
                projectMapper.deleteByWareId(wareId);
            }
            projectMapper.batchInsert(list);
        }
    }


    @Override
    public InsuranceWare getById(String id) {
        return insuranceWareMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public void update(InsuranceWare param) {
        insuranceWareMapper.updateByPrimaryKeySelective(param);
    }


    @Override
    public InsuranceWareExtWithBLOBs getExtById(String id) {
        
        InsuranceWareExtWithBLOBs ext = insuranceWareMapper.selectBlobById(id);
        
        return ext;
    }


    @Override
    public List<InsuranceProject> getWareProject(String wareId) {
        return projectMapper.selectByWareId(wareId);
    }


    @Override
    public void wareOnline() throws Exception {
        
        InsuranceWareParam param = new InsuranceWareParam();
        param.setWareStatus(Constant.WARE_STATUS_AUDIT_OK + "");
        List<InsuranceWare> list = insuranceWareMapper.selectByParam(param);
        
        for(InsuranceWare ware : list){
            
            if(ware.getOnlineTime().before(new Date())){
                
                // 同步商品到平台
                List<InsuranceSku> skuList = skuMapper.selectByWareId(ware.getId());
                brickWareTransService.transWare(ware, skuList, "上架");
                
                // 上架
                ware.setStatus(Constant.WARE_STATUS_ONLINE);
                insuranceWareMapper.updateByPrimaryKeySelective(ware);
                
                // 更新brick_sku_id
                for(InsuranceSku sku : skuList){
                    skuMapper.updateByPrimaryKeySelective(sku);
                }
            }
        }
    }


    @Override
    public void wareOffline() {
        InsuranceWareParam param = new InsuranceWareParam();
        param.setWareStatus(Constant.WARE_STATUS_ONLINE + "");
        
        List<InsuranceWare> list = insuranceWareMapper.selectByParam(param);
        
        for(InsuranceWare ware : list){
            if(ware.getOfflineTime().before(new Date())){
                ware.setStatus(Constant.WARE_STATUS_OFFLINE);
                insuranceWareMapper.updateByPrimaryKey(ware);
            }
        }
    }


	@Override
	public List<InsuranceWareExtWithBLOBs> getAllWareList() {
		return insuranceWareMapper.selectAllWareList();
	}

}
  
