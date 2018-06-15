package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class InsuranceGetGuaranteeDetailRequest extends BaseRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExplainAnnotation (explain = "商品ID")
	@NotNullValidate
	private String wareId;

	public String getWareId() {
		return wareId;
	}

	public void setWareId(String wareId) {
		this.wareId = wareId;
	}
	

}
