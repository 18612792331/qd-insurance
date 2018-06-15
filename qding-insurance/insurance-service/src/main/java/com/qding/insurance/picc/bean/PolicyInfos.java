package com.qding.insurance.picc.bean;

import java.util.List;

/**
 * 投保请求报文中的PolicyInfos节点
 * @author zhangxiaojun
 *
 */
public class PolicyInfos {
	//PolicyInfo节点的集合
	private List<PolicyInfo> policyInfoList;

	public List<PolicyInfo> getPolicyInfoList() {
		return policyInfoList;
	}

	public void setPolicyInfoList(List<PolicyInfo> policyInfoList) {
		this.policyInfoList = policyInfoList;
	}
	
	
}
