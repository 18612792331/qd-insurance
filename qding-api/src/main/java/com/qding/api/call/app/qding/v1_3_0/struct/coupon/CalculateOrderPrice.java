package com.qding.api.call.app.qding.v1_3_0.struct.coupon;



import com.qding.order.domain.OrderPromotion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 */
public class CalculateOrderPrice implements Serializable {


    private static final long serialVersionUID = -5929638575928671846L;

    private String totalPrice;

    private String totalDiscountPrice;

    private String shouldPay;

    private String expressPrice;

    private List<OrderPromotion> promotionList;

    //优惠券金额是否超出实际金额
    private Boolean isShowNotice;

    //备注
    private String notice;

    public List<OrderPromotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<OrderPromotion> promotionList) {
        this.promotionList = promotionList;
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

    public Boolean getIsShowNotice() {
        return isShowNotice;
    }

    public void setIsShowNotice(Boolean isShowNotice) {
        this.isShowNotice = isShowNotice;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }
}
