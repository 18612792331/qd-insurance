package com.qding.insurance.picc.bean;

import java.util.List;

/**
 * PICC接口调用返回xml子节点InsuredReturns
 * @author zhangxiaojun
 *
 */
public class InsuredReturns {
	/** InsuredReturn节点的集合 */
	private List<InsuredReturn> ireList;

	public List<InsuredReturn> getIreList() {
		return ireList;
	}

	public void setIreList(List<InsuredReturn> ireList) {
		this.ireList = ireList;
	}
	
	
}
