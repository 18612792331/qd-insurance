package com.qding.insurance.picc.dto;

import com.qding.insurance.picc.bean.PolicyInfos;


/**
 * webservice调用PICC接口请求参数封装类
 * @author zhangxiaojun
 *
 */
public class PICCInsureRequestParamDto {
	//保单信息
	private PolicyInfos policyInfos;

	public PolicyInfos getPolicyInfos() {
		return policyInfos;
	}

	public void setPolicyInfos(PolicyInfos policyInfos) {
		this.policyInfos = policyInfos;
	}
	
	
}
