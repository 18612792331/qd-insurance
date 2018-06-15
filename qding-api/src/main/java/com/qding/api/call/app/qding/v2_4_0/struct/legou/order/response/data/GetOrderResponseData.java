package com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Order;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/12/29.
 */
public class GetOrderResponseData extends ResponseData {

    private static final long serialVersionUID = -5384312937490257040L;

    @ExplainAnnotation(explain = "订单实体")
    private Order entity;

    public Order getEntity() {
        return entity;
    }

    public void setEntity(Order entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetOrderResponseData{" +
                "entity=" + entity +
                '}';
    }
}
