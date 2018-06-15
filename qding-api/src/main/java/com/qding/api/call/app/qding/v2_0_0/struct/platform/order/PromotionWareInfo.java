package com.qding.api.call.app.qding.v2_0_0.struct.platform.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

public class PromotionWareInfo implements Serializable {

/*    private List<PromotionSkuInfo> promotionSkuInfoList;*/

    @ExplainAnnotation(explain ="商品ID" )
    private Long wareId;

    @ExplainAnnotation(explain ="是否有促销" )
    private boolean isPromotion ;

    @ExplainAnnotation(explain ="促销标签列表" )
    private List<PromotionLabel> promotionLabelList;

    @ExplainAnnotation(explain =" 选中sku促销信息" )
    private PromotionSkuInfo currentSkuInfo;

    public PromotionWareInfo() {
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public List<PromotionLabel> getPromotionLabelList() {
        return promotionLabelList;
    }

    public void setPromotionLabelList(List<PromotionLabel> promotionLabelList) {
        this.promotionLabelList = promotionLabelList;
    }

    public PromotionSkuInfo getCurrentSkuInfo() {
        return currentSkuInfo;
    }

    public void setCurrentSkuInfo(PromotionSkuInfo currentSkuInfo) {
        this.currentSkuInfo = currentSkuInfo;
    }
}
