package com.qding.insurance.picc.bean;

/**
 * 投保请求报文中的Insured节点
 * @author zhangxiaojun
 *
 */
public class Insured {
	//被保险人姓名
	private String insuredName;
	//被保险人证件类型
	private String insuredIdType;
	//被保险人证件号
	private String insuredIdNo;
	//被保险人邮箱
	private String insuredEmail;
	
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredIdType() {
		return insuredIdType;
	}
	public void setInsuredIdType(String insuredIdType) {
		this.insuredIdType = insuredIdType;
	}
	public String getInsuredIdNo() {
		return insuredIdNo;
	}
	public void setInsuredIdNo(String insuredIdNo) {
		this.insuredIdNo = insuredIdNo;
	}
	public String getInsuredEmail() {
		return insuredEmail;
	}
	public void setInsuredEmail(String insuredEmail) {
		this.insuredEmail = insuredEmail;
	}
	
}
