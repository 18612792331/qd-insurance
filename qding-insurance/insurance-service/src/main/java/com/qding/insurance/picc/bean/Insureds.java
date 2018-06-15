package com.qding.insurance.picc.bean;

import java.util.List;

/**
 * 投保请求报文中的Insureds节点
 * @author zhangxiaojun
 *
 */
public class Insureds {
	//Insured节点的集合
	private List<Insured> insuredList;

	public List<Insured> getInsuredList() {
		return insuredList;
	}

	public void setInsuredList(List<Insured> insuredList) {
		this.insuredList = insuredList;
	}
	
	
}
