package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/5/25.
 */
public class GetOrderPayStatusRequest extends BaseRequest {


    private static final long serialVersionUID = 599933766388587872L;


    @ExplainAnnotation (explain = "订单号")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "GetOrderPayStatusRequest{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
