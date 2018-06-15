package com.qding.api.call.app.qding.v1_3_1.struct.payment.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class WalletPayRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820736381930378707L;

	@NotNullValidate
	private String orderId;
	
	private String password;
	
	public WalletPayRequest() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
