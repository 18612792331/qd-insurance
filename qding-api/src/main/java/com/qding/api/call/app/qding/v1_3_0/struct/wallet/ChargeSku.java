package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import java.io.Serializable;

public class ChargeSku implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710207970703975647L;

	private String skuId;
	
	private String goodsName;
	
	private String price;

	private String promotion;
	
	public ChargeSku() {

	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
