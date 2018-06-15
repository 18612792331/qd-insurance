package com.qding.insurance.rpc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qding.framework.common.service.order.ProductOrderBean;
import com.qding.insurance.rpc.IInsuranceOrderListRpcService;
import com.qding.insurance.service.IInsuranceSkuService;
@Service(value="insuranceOrderListRpcService")
public class InsuranceOrderListRpcServiceImpl implements IInsuranceOrderListRpcService{

	@Autowired
	private IInsuranceSkuService insuranceSkuService;
	
	/**
     * * 通过订单号批量获取订单状态先关信息
     * @param orderCodes  订单号列表
     * @return
     * 订单状态信息Bean的List
     */
	@Override
	public List<ProductOrderBean> getProductOrder(List<String> orderCodes) {
		return insuranceSkuService.getProductOrder(orderCodes);
	}


    /**
     * 根据平台skuId获取货品详情信息(Json格式)
     * @param skuId
     * @return
     */
	@Override
	public String getGoodsJson(Long skuId) {
		return JSON.toJSONString(insuranceSkuService.getByBrickSkuId(String.valueOf(skuId)));
	}
	
	
	
	 
 

}
