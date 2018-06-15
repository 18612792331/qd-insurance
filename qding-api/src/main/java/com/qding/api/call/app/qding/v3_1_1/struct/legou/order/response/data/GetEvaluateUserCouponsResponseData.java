package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateCouponBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/6/23.
 */
public class GetEvaluateUserCouponsResponseData extends ResponseData {

    private static final long serialVersionUID = -8748998452080198903L;

    @ExplainAnnotation(explain = "优惠券")
    private List<EvaluateCouponBean> couponList;

    public List<EvaluateCouponBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<EvaluateCouponBean> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "GetEvaluateUserCouponsResponseData{" +
                "couponList=" + couponList +
                '}';
    }
}
