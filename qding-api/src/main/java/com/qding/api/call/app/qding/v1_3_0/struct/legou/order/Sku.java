package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class Sku implements Serializable{

	private static final long serialVersionUID = 6324347918085537774L;

	@NotNullValidate
	@ExplainAnnotation (explain = "货品ID")
	private String skuId;
	
	@MinValueValidate(value="1")
	@ExplainAnnotation (explain = "购买数")
	private Integer buyNum;

	public Sku() {

	}

	public Sku(String skuId, Integer buyNum) {
		super();
		this.skuId = skuId;
		this.buyNum = buyNum;
	}


	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getBuyNum() {
		return buyNum;
	}
	
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	
}
