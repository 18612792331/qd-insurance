package com.qding.api.call.app.qding.v2_3_0.struct.payment.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetPayMethodRequest extends BaseRequest{

	private static final long serialVersionUID = -2354272738810897114L;

	@NotNullValidate
	@ExplainAnnotation(explain = "会员ID")
	private String memberId;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "支付的业态类型",desc = "详情可询问嗣强")
	private String payBusinessType;

	@NotNullValidate
	@ExplainAnnotation(explain = "订单号")
	private String orderId;

	public GetPayMethodRequest() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public String getPayBusinessType() {
		return payBusinessType;
	}

	public void setPayBusinessType(String payBusinessType) {
		this.payBusinessType = payBusinessType;
	}

	@Override
	public String toString() {
		return "GetPayMethodRequest{" +
				"memberId='" + memberId + '\'' +
				", payBusinessType='" + payBusinessType + '\'' +
				", orderId='" + orderId + '\'' +
				'}';
	}
}
