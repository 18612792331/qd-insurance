package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2016/6/16.
 */
public class GoodsPromotion  extends  SkipUrl {

    @ExplainAnnotation (explain = "促销名称")
    private String promotionTag;

    @ExplainAnnotation (explain = "促销描述")
    private String promotionName;

    @ExplainAnnotation (explain = "促销后价格")
    private String promotionPrice;

    @ExplainAnnotation (explain = "促销ID")
    private String promotionId;

    @ExplainAnnotation (explain = "促销类型")
    private Integer promotionType;

    @ExplainAnnotation(explain = "是否支持超出限购原价购买",desc = "0：不支持 1：支持")
    private Integer limitStrategy;
    
    @ExplainAnnotation(explain = "促销状态")
    private Integer status;

    public Integer getLimitStrategy() {
        return limitStrategy;
    }

    public void setLimitStrategy(Integer limitStrategy) {
        this.limitStrategy = limitStrategy;
    }

    public String getPromotionTag() {
        return promotionTag;
    }

    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
    
    
    
}
