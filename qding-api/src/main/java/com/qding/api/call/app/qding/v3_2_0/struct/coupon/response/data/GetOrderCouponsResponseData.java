package com.qding.api.call.app.qding.v3_2_0.struct.coupon.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.CouponDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/10.
 */
public class GetOrderCouponsResponseData   extends ResponseData {


    private static final long serialVersionUID = 5988612393768992010L;

 
    @ExplainAnnotation(explain="可用优惠券")
    private List<CouponDto> commonCoupons;

   
    @ExplainAnnotation(explain="不可用优惠券")
    List<CouponDto> unavailableCouponList;

    @ExplainAnnotation(explain="总数")
    private int totalCount;

	public List<CouponDto> getCommonCoupons() {
		return commonCoupons;
	}

	public void setCommonCoupons(List<CouponDto> commonCoupons) {
		this.commonCoupons = commonCoupons;
	}

	public List<CouponDto> getUnavailableCouponList() {
		return unavailableCouponList;
	}

	public void setUnavailableCouponList(List<CouponDto> unavailableCouponList) {
		this.unavailableCouponList = unavailableCouponList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
    
    

     
}
