package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by jinhaishan on 17/7/20.
 */
public class SalesBoard extends BoardImg{
    private static final long serialVersionUID = 5711871459771763356L;

    @ExplainAnnotation(explain = "市场价")
    private String marketPrice;

    @ExplainAnnotation(explain = "营销价")
    private String promotionPrice;

    @ExplainAnnotation(explain = "结束时间")
    private Long endTime;

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
