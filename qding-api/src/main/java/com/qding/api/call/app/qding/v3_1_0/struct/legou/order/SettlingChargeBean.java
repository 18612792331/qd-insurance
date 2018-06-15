package com.qding.api.call.app.qding.v3_1_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/5/17.
 */
public class SettlingChargeBean implements Serializable {

    private static final long serialVersionUID = 8747937651886344370L;

    @ExplainAnnotation (explain = "商品结算查询类型",desc = "1:每日鲜，2：其他")
    private Integer chargeType;

    @ExplainAnnotation(explain = "订单总金额")
    private String totalPrice;

    @ExplainAnnotation (explain = "总折扣金额")
    private String totalDiscountPrice;

    @ExplainAnnotation (explain = "应付金额")
    private String shouldPay;

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(String totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public String getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay = shouldPay;
    }
}
