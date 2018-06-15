package com.qding.insurance.picc.bean;

import java.util.List;

/**
 * PICC接口调用返回xml子节点PolicyInfoReturns
 * @author zhangxiaojun
 *
 */
public class PolicyInfoReturns {
	/** PolicyInfoReturn节点的集合 */
	private List<PolicyInfoReturn> pireList;

	public List<PolicyInfoReturn> getPireList() {
		return pireList;
	}

	public void setPireList(List<PolicyInfoReturn> pireList) {
		this.pireList = pireList;
	}
	
}
