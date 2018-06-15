package com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.coupon.BindCoupon;
import com.qding.api.struct.ResponseData;

/**
 * 用户绑定优惠券					
 * @author lichao
 *
 */
public class BindCouponsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7503057531732803113L;
	
	private BindCoupon entity;
	
	public BindCouponsResponseData() {

	}

	public BindCouponsResponseData(BindCoupon entity) {
		super();
		this.entity = entity;
	}


	public BindCoupon getEntity() {
		return entity;
	}


	public void setEntity(BindCoupon entity) {
		this.entity = entity;
	}
	
}
