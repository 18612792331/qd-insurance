package com.qding.api.call.app.qding.v3_2_0.struct.coupon.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.CouponDto;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/9/10.
 */
public class GetCouponsBymidResponseData extends ResponseData {


    private static final long serialVersionUID = 5988612393768992010L;

    @ExplainAnnotation(explain = "优惠券列表")
    private List<CouponDto> commonCoupons;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CouponDto> getCommonCoupons() {
        return commonCoupons;
    }

    public void setCommonCoupons(List<CouponDto> commonCoupons) {
        this.commonCoupons = commonCoupons;
    }

    @Override
    public String toString() {
        return "GetCouponListResponseData{" +
                "commonCoupons=" + commonCoupons +
                ", totalCount=" + totalCount +
                '}';
    }
}
