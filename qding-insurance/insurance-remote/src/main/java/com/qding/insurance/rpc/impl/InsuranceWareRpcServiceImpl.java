package com.qding.insurance.rpc.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.insurance.dao.InsuranceWareExtMapper;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.rpc.IInsuranceWareRpcService;
import com.qding.insurance.service.IInsuranceWareService;

@Service("insuranceWareRpc")
public class InsuranceWareRpcServiceImpl implements IInsuranceWareRpcService {
	private static final Logger logger = Logger.getLogger(InsuranceWareRpcServiceImpl.class);

	@Autowired
	private IInsuranceWareService wareService;
	
	@Override
	public InsuranceWareExtWithBLOBs getGuaranteeDetail(String wareId) {
		return wareService.getExtById(wareId);
	}

	@Override
	public List<InsuranceWareExtWithBLOBs> getAllWareList() {
		return wareService.getAllWareList();
	}



}
