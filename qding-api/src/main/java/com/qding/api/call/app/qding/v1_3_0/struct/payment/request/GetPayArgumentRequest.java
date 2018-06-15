package com.qding.api.call.app.qding.v1_3_0.struct.payment.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetPayArgumentRequest extends BaseRequest{

	private static final long serialVersionUID = -1332541270774233144L;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "支付类型")
	private Integer type;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "订单号")
	private String orderId;

	@ExplainAnnotation(explain = "组合支付支付方式",desc = "1:钱包，2：积分")
	private Integer combinationPayType;

	public GetPayArgumentRequest() {

	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getCombinationPayType() {
		return combinationPayType;
	}

	public void setCombinationPayType(Integer combinationPayType) {
		this.combinationPayType = combinationPayType;
	}
}
