package com.qding.insurance.rpc;

import java.util.List;

import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;



/**
 * Create by zhangxiaojun on 18/6/7
 **/
public interface IInsuranceWareRpcService {
	
	public InsuranceWareExtWithBLOBs getGuaranteeDetail(String wareId);

	public List<InsuranceWareExtWithBLOBs> getAllWareList();
}
