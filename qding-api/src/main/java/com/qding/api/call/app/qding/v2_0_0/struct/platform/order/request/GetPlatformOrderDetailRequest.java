package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/3/17.
 */
public class GetPlatformOrderDetailRequest extends BaseRequest {

    @ExplainAnnotation (explain = "订单号")
    @NotNullValidate
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "GetPlatformOrderDetailRequest{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
