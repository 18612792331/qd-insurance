package com.qding.api.call.app.qding.v1_3_0.struct.history.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetOrderRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5174591444600453992L;

	@NotNullValidate
	private String orderId;
	
	public GetOrderRequest() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
