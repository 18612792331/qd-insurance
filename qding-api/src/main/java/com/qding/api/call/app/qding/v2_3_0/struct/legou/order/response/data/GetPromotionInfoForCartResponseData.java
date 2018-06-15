package com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_0.struct.legou.order.ConfirmOrder;
import com.qding.api.struct.ResponseData;


/**
 * Created by qd on 2016/5/19.
 */
public class GetPromotionInfoForCartResponseData extends ResponseData {

    private static final long serialVersionUID = 8823319271536047821L;

    @ExplainAnnotation (explain = "优惠信息")
    private ConfirmOrder entity;

    public ConfirmOrder getEntity() {
        return entity;
    }

    public void setEntity(ConfirmOrder entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetPromotionInfoForCartResponseData{" +
                "entity=" + entity +
                '}';
    }
}
