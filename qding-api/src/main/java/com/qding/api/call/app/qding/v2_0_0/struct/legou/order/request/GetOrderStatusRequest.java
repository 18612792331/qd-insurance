package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;


/**
 * Created by qd on 2016/1/5.
 */
public class GetOrderStatusRequest extends BaseRequest {

    private static final long serialVersionUID = 8751539838060696929L;

    @ExplainAnnotation(explain = "待查询分组订单json串")
    private String orderParametere;

    public String getOrderParametere() {
        return orderParametere;
    }

    public void setOrderParametere(String orderParametere) {
        this.orderParametere = orderParametere;
    }

    @Override
    public String toString() {
        return "GetOrderStatusRequest{" +
                "orderParametere=" + orderParametere +
                '}';
    }
}
