package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import java.io.Serializable;
import java.math.BigDecimal;

public class OnlinePayMethod extends PayMethodBean implements Serializable{

	private static final long serialVersionUID = 4818777258014138813L;

	public OnlinePayMethod() {

	}

	public OnlinePayMethod(Integer type, String name, String icon, String desc, String activity, Integer defaultFlag, String combinationShouldPay, String quotaAmount) {
		super(type, name, icon, desc, activity, defaultFlag, combinationShouldPay, quotaAmount);
	}

}
