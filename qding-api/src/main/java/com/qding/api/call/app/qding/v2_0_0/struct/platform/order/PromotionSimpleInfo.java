package com.qding.api.call.app.qding.v2_0_0.struct.platform.order;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class PromotionSimpleInfo implements Serializable {

    @ExplainAnnotation(explain = "促销ID")
    private String promotionId;

    @ExplainAnnotation(explain = "优惠掉的价格")
    private String discountPrice;

    @ExplainAnnotation(explain = "促销标签")
    private PromotionLabel promotionLabel;

    @ExplainAnnotation(explain = "促销名称")
    private String promotionName;

    @ExplainAnnotation(explain = "促销类型",desc = " 1 商品促销 2 订单促销 6 阶梯团购")
    private Integer promotionType;

    public PromotionSimpleInfo() {
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public PromotionLabel getPromotionLabel() {
        return this.promotionLabel;
    }

    public void setPromotionLabel(PromotionLabel promotionLabel) {
        this.promotionLabel = promotionLabel;
    }

    public String getPromotionName() {
        return this.promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Integer getPromotionType() {
        return this.promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }
}