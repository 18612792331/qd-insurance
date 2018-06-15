package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 确认收货		
 * @author lichao
 *
 */
@Validate
public class ConfirmReceiptGoodsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3963057808899074051L;
 
	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private String subOrderCode;
	
	public ConfirmReceiptGoodsRequest() {
	}

	public ConfirmReceiptGoodsRequest(String memberId, String subOrderCode) {
		super();
		this.memberId = memberId;
		this.subOrderCode = subOrderCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	
	public String getSubOrderCode() {
		return subOrderCode;
	}
}
