package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;

/**
 * Created by zhangxiaojun on 2018/6/7.
 */
public class InsuranceGetGuaranteeDetailResponseData extends ResponseData {
	private static final long serialVersionUID = 9153545829841806312L;
	
	@ExplainAnnotation(explain = "商品扩展信息")
	private InsuranceWareExtWithBLOBs insuranceWareExtWithBLOBs;

	public InsuranceWareExtWithBLOBs getInsuranceWareExtWithBLOBs() {
		return insuranceWareExtWithBLOBs;
	}

	public void setInsuranceWareExtWithBLOBs(
			InsuranceWareExtWithBLOBs insuranceWareExtWithBLOBs) {
		this.insuranceWareExtWithBLOBs = insuranceWareExtWithBLOBs;
	}



}
