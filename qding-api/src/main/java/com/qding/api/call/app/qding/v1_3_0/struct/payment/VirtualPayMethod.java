package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import com.qding.api.call.app.qding.v2_0_0.struct.payment.FamilyPayBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class VirtualPayMethod extends  PayMethodBean implements Serializable{

	private static final long serialVersionUID = 4818777258014138813L;


	private String value;

	private List<FamilyPayBean> familyPayBean;

	public VirtualPayMethod() {

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<FamilyPayBean> getFamilyPayBean() {
		return familyPayBean;
	}

	public void setFamilyPayBean(List<FamilyPayBean> familyPayBean) {
		this.familyPayBean = familyPayBean;
	}

	public VirtualPayMethod(String value, List<FamilyPayBean> familyPayBean) {
		this.value = value;
		this.familyPayBean = familyPayBean;
	}

	public VirtualPayMethod(Integer type, String name, String icon, String desc, String activity, Integer defaultFlag, String combinationShouldPay, String quotaAmount, String value, List<FamilyPayBean> familyPayBean) {
		super(type, name, icon, desc, activity, defaultFlag, combinationShouldPay, quotaAmount);
		this.value = value;
		this.familyPayBean = familyPayBean;
	}
}
