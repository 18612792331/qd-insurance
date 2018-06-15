package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import java.io.Serializable;
import java.math.BigDecimal;

public class OfflinePayMethod extends PayMethodBean implements Serializable{

	private static final long serialVersionUID = 8079462587079151184L;

	public OfflinePayMethod() {
	}

	public OfflinePayMethod(Integer type, String name, String icon, String desc, String activity, Integer defaultFlag, String combinationShouldPay, String quotaAmount) {
		super(type, name, icon, desc, activity, defaultFlag, combinationShouldPay, quotaAmount);
	}
}
