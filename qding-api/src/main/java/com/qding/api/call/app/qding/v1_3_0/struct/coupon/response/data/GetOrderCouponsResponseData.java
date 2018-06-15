package com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.coupon.Coupon;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.CouponBatch;
import com.qding.api.struct.ResponseData;

/**
 * 获取用户某个项目下某个业态的所有可用优惠券(不包含未生效的)					
 * @author lichao
 *
 */
public class GetOrderCouponsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -224401878133063838L;
 
	private List<Coupon> commonCoupons;
	
	private List<CouponBatch> specialCoupons;
	
	public GetOrderCouponsResponseData() {

	}

	public GetOrderCouponsResponseData(List<Coupon> commonCoupons,
			List<CouponBatch> specialCoupons) {
		super();
		this.commonCoupons = commonCoupons;
		this.specialCoupons = specialCoupons;
	}

	public List<Coupon> getCommonCoupons() {
		return commonCoupons;
	}

	public void setCommonCoupons(List<Coupon> commonCoupons) {
		this.commonCoupons = commonCoupons;
	}

	public List<CouponBatch> getSpecialCoupons() {
		return specialCoupons;
	}

	public void setSpecialCoupons(List<CouponBatch> specialCoupons) {
		this.specialCoupons = specialCoupons;
	}

	@Override
	public String toString() {
		return "GetOrderCouponsResponseData [commonCoupons="
				+ commonCoupons + ", specialCoupons=" + specialCoupons
				+ ", toString()=" + super.toString() + "]";
	}

}
