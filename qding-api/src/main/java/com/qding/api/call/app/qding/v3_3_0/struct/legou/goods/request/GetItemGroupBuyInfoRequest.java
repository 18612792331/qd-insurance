package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class GetItemGroupBuyInfoRequest extends BaseRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ExplainAnnotation(explain = "skuid")
	@NotNullValidate
	private String skuId;


	public String getSkuId() {
		return skuId;
	}


	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}


	@Override
	public String toString() {
		return "GetItemGroupBuyInfoRequest [skuId=" + skuId + "]";
	}
	

	
	
	
}
