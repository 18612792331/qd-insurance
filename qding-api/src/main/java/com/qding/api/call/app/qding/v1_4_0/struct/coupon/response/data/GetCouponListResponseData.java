package com.qding.api.call.app.qding.v1_4_0.struct.coupon.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.coupon.Coupon;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/9/10.
 */
public class GetCouponListResponseData   extends ResponseData {


    private static final long serialVersionUID = 5988612393768992010L;

    /**
     * 优惠券
     */
    private List<Coupon> commonCoupons;

    /**
     * 不可用优惠券
     */
    List<Coupon> unavailableCouponList;


    /**
     * 总记录数
     */
    private int totalCount;

    public List<Coupon> getUnavailableCouponList() {
        return unavailableCouponList;
    }

    public void setUnavailableCouponList(List<Coupon> unavailableCouponList) {
        this.unavailableCouponList = unavailableCouponList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Coupon> getCommonCoupons() {
        return commonCoupons;
    }

    public void setCommonCoupons(List<Coupon> commonCoupons) {
        this.commonCoupons = commonCoupons;
    }

    @Override
    public String toString() {
        return "GetCouponListResponseData{" +
                "commonCoupons=" + commonCoupons +
                ", unavailableCouponList=" + unavailableCouponList +
                ", totalCount=" + totalCount +
                '}';
    }
}
