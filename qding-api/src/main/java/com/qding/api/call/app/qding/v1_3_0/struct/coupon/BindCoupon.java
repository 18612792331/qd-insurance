package com.qding.api.call.app.qding.v1_3_0.struct.coupon;

import java.io.Serializable;

public class BindCoupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7767871462411520426L;
	
	public BindCoupon() {

	}
	
	//1：代表地区券 2：代表通用券
	private Integer couponTemplateType;
	
	public Integer getCouponTemplateType() {
		return couponTemplateType;
	}

	public void setCouponTemplateType(Integer couponTemplateType) {
		this.couponTemplateType = couponTemplateType;
	}
}
