package com.qding.api.call.app.qding.v3_0_1.struct.groupon.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by jinhaishan on 17/4/14.
 */
public class GoodsInfoResponseDta extends ResponseData{

    @ExplainAnnotation(explain = "业态对应的商品信息")
    private String goodsJson;

    public String getGoodsJson() {
        return goodsJson;
    }

    public void setGoodsJson(String goodsJson) {
        this.goodsJson = goodsJson;
    }
}
