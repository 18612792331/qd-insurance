package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import java.io.Serializable;

public class AlipayArgument implements Serializable{

	private static final long serialVersionUID = 3392948624071229117L;
	
	private String sign;

	public AlipayArgument() {

	}

	public AlipayArgument(String sign) {
		super();
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
