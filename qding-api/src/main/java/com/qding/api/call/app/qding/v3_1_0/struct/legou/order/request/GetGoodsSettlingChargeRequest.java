package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.SkuSettlingCharge;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qd on 2017/5/16.
 */
@Validate
public class GetGoodsSettlingChargeRequest extends BaseRequest {

    private static final long serialVersionUID = 8265155984863076644L;

    @NotNullValidate
    @ExplainAnnotation(explain = "订单来源")
    private Integer orderSourceType;

    @ExplainAnnotation (explain = "可用千丁券")
    private String[] couponCodes;

    @ExplainAnnotation (explain = "订单促销ID")
    private List<String> orderPromotionIds;

    @ExplainAnnotation (explain = "购物车商品")
    private List<SkuSettlingCharge> skus;

    @NotNullValidate
    @ExplainAnnotation (explain = "是否使用优惠券",desc = "0:首次调用(最优)，1：其他，2：未选券调用")
    private Integer isUserChooseCoupon;

    public Integer getOrderSourceType() {
        return orderSourceType;
    }

    public void setOrderSourceType(Integer orderSourceType) {
        this.orderSourceType = orderSourceType;
    }

    public String[] getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(String[] couponCodes) {
        this.couponCodes = couponCodes;
    }

    public List<String> getOrderPromotionIds() {
        return orderPromotionIds;
    }

    public void setOrderPromotionIds(List<String> orderPromotionIds) {
        this.orderPromotionIds = orderPromotionIds;
    }

    public List<SkuSettlingCharge> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuSettlingCharge> skus) {
        this.skus = skus;
    }

    public Integer getIsUserChooseCoupon() {
        return isUserChooseCoupon;
    }

    public void setIsUserChooseCoupon(Integer isUserChooseCoupon) {
        this.isUserChooseCoupon = isUserChooseCoupon;
    }

    @Override
    public String toString() {
        return "GetGoodsSettlingChargeRequest{" +
                "orderSourceType=" + orderSourceType +
                ", couponCodes=" + Arrays.toString(couponCodes) +
                ", orderPromotionIds=" + orderPromotionIds +
                ", skus=" + skus +
                ", isUserChooseCoupon=" + isUserChooseCoupon +
                '}';
    }
}
