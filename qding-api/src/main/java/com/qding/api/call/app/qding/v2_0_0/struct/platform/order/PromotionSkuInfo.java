package com.qding.api.call.app.qding.v2_0_0.struct.platform.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

public class PromotionSkuInfo implements Serializable {

    @ExplainAnnotation (explain = "货品id")
    private Long skuId;
    @ExplainAnnotation (explain = "商品id")
    private Long wareId;
    @ExplainAnnotation (explain = "促销价")
    private String promotionPrice;
    @ExplainAnnotation (explain = "市场价")
    private String originalPrice;
    @ExplainAnnotation (explain = "最低价")
    private String lowPrice;
    @ExplainAnnotation (explain = "最高价")
    private String upPrice;
    @ExplainAnnotation (explain = "促销简要信息列表")
    private List<PromotionSimpleInfo> promotionSimpleInfo;
    private Integer promotionLevel = Integer.valueOf(0);

    public PromotionSkuInfo() {
    }

    public Long getSkuId() {
        return this.skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public List<PromotionSimpleInfo> getPromotionSimpleInfo() {
        return this.promotionSimpleInfo;
    }

    public void setPromotionSimpleInfo(List<PromotionSimpleInfo> promotionSimpleInfo) {
        this.promotionSimpleInfo = promotionSimpleInfo;
    }

    public String getPromotionPrice() {
        return this.promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getLowPrice() {
        return this.lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getUpPrice() {
        return this.upPrice;
    }

    public void setUpPrice(String upPrice) {
        this.upPrice = upPrice;
    }

    public Long getWareId() {
        return this.wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Integer getPromotionLevel() {
        return this.promotionLevel;
    }

    public void setPromotionLevel(Integer promotionLevel) {
        this.promotionLevel = promotionLevel;
    }
}
