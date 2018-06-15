package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/6/23.
 */
public class EvaluateCouponBean implements Serializable {

    private static final long serialVersionUID = 1665270488898829447L;

    @ExplainAnnotation (explain = "券分号")
    private String couponId;

    @ExplainAnnotation (explain = "优惠方案名称")
    private String couponName;

    @ExplainAnnotation (explain = "券金额")
    private String couponPrice;

    @ExplainAnnotation (explain = "券的有效开始时间")
    private long validStart;

    @ExplainAnnotation (explain = "券的有效结束时间")
    private long validEnd;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        this.couponPrice = couponPrice;
    }

    public long getValidStart() {
        return validStart;
    }

    public void setValidStart(long validStart) {
        this.validStart = validStart;
    }

    public long getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(long validEnd) {
        this.validEnd = validEnd;
    }
}
