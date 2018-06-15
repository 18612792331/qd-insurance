package com.qding.insurance.service;

import java.util.List;

import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.InsuranceProject;
import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.param.InsuranceWareParam;
import com.qding.insurance.param.SaveWareParam;

public interface IInsuranceWareService {
    
    ResultPage<InsuranceWare> getResultPage(InsuranceWareParam param);
    
    void add(SaveWareParam param);
    
    void update(SaveWareParam param);
    
    void update(InsuranceWare param);
    
    InsuranceWare getById(String id);
    
    InsuranceWareExtWithBLOBs getExtById(String id);
    
    List<InsuranceProject> getWareProject(String wareId);
    
    void wareOnline()  throws Exception;
    
    void wareOffline();

	List<InsuranceWareExtWithBLOBs> getAllWareList();
}
  
