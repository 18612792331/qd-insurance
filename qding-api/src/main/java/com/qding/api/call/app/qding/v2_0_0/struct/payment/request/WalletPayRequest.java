package com.qding.api.call.app.qding.v2_0_0.struct.payment.request;

import com.qding.api.annotation.ExplainAnnotation;
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
	@ExplainAnnotation(explain = "订单号")
	private String orderId;

	@NotNullValidate
	@ExplainAnnotation(explain = "支付人ID",desc = "谁付钱就是谁")
	private String payMemberId;

	@ExplainAnnotation(explain = "支付密码")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayMemberId() {
		return payMemberId;
	}

	public void setPayMemberId(String payMemberId) {
		this.payMemberId = payMemberId;
	}

	public WalletPayRequest() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
