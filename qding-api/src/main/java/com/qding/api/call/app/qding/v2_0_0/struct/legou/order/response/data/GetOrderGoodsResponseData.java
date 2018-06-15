package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGoods;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/16.
 */
public class GetOrderGoodsResponseData extends ResponseData {

    @ExplainAnnotation(explain = "订单商品列表实体")
    private OrderBean entity;

    public OrderBean getEntity() {
        return entity;
    }

    public void setEntity(OrderBean entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetOrderGoodsResponseData{" +
                "entity=" + entity +
                '}';
    }
}
