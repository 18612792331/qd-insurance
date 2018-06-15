package com.qding.insurance.service;

import java.util.List;

import com.qding.framework.common.service.order.ProductOrderBean;
import com.qding.insurance.domain.InsuranceSku;

public interface IInsuranceSkuService {
    
    void addInsuranceSku(List<InsuranceSku> list);
    
    List<InsuranceSku> getWareSku(String wareId);
    
    InsuranceSku getById(String id);
    
    InsuranceSku getByBrickSkuId(String skuId);
    
	public List<ProductOrderBean> getProductOrder(List<String> orderCodes);
}
  
