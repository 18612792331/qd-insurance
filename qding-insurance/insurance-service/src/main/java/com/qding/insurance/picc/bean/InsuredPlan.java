package com.qding.insurance.picc.bean;

/**
 * 投保请求报文中的InsuredPlan节点
 * @author zhangxiaojun
 *
 */
public class InsuredPlan {
	//定额方案代码/多方案固定传Multischeme
	private String RationType;
	
	public String getRationType() {
		return RationType;
	}
	public void setRationType(String rationType) {
		RationType = rationType;
	}
	
}
