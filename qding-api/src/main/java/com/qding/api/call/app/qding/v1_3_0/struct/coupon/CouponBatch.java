package com.qding.api.call.app.qding.v1_3_0.struct.coupon;

import java.io.Serializable;
import java.util.List;

public class CouponBatch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2120778608491815043L;

	private String id;
	
	private String name;
	
	private List<Coupon> coupons;
	
	public CouponBatch() {

	}

	public CouponBatch(String id, String name, List<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.coupons = coupons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "CouponBatch [id=" + id + ", name=" + name + ", coupons="
				+ coupons + "]";
	}

}
