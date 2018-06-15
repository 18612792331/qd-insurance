package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.PromotionWareInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Description
 * Created by ygd on 2017/9/14.
 */
public class WarePromotionInfoResponse extends ResponseData {

    @ExplainAnnotation(explain = "促销信息列表")
    private List<PromotionWareInfo> promotionWareInfoList;

    public List<PromotionWareInfo> getPromotionWareInfoList() {
        return promotionWareInfoList;
    }

    public void setPromotionWareInfoList(List<PromotionWareInfo> promotionWareInfoList) {
        this.promotionWareInfoList = promotionWareInfoList;
    }

    @Override
    public String toString() {
        return "WarePromotionInfoResponse{" +
                "promotionWareInfoList=" + promotionWareInfoList +
                '}';
    }
}
