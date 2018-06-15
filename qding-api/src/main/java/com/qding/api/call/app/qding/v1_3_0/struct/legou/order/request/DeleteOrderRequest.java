package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 删除订单		
 * @author lichao
 *
 */
@Validate
public class DeleteOrderRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3963057808899074051L;
 
	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private String orderCode;
	
	public DeleteOrderRequest() {
	}

	public DeleteOrderRequest(String memberId, String orderCode) {
		super();
		this.memberId = memberId;
		this.orderCode = orderCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public String toString() {
		return "DeleteOrderRequest [memberId=" + memberId + ", orderCode="
				+ orderCode + ", toString()=" + super.toString() + "]";
	}
	
}
