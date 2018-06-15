package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.PromotionSkuInfo;
import com.qding.api.struct.ResponseData;

/**
 * Description
 * Created by ygd on 2017/9/14.
 */
public class SkuPromotionInfoResponse  extends ResponseData {

    @ExplainAnnotation (explain = "促销信息")
    private PromotionSkuInfo promotionSkuInfo;

    public PromotionSkuInfo getPromotionSkuInfo() {
        return promotionSkuInfo;
    }

    public void setPromotionSkuInfo(PromotionSkuInfo promotionSkuInfo) {
        this.promotionSkuInfo = promotionSkuInfo;
    }

    @Override
    public String toString() {
        return "SkuPromotionInfoResponse{" +
                "promotionSkuInfo=" + promotionSkuInfo +
                '}';
    }
}
