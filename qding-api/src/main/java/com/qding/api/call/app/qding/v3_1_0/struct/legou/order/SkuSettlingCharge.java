package com.qding.api.call.app.qding.v3_1_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;

/**
 * Created by qd on 2017/5/17.
 */
public class SkuSettlingCharge extends Sku {

    @ExplainAnnotation (explain = "商品结算查询类型",desc = "1:每日鲜，2：其他")
    private Integer chargeType;

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }
}
