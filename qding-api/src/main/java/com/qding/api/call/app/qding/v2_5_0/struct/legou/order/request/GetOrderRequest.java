package com.qding.api.call.app.qding.v2_5_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/12/29.
 */
@Validate
public class GetOrderRequest extends BaseRequest {

    private static final long serialVersionUID = -1234041321545844903L;


    @NotNullValidate
    @ExplainAnnotation(explain = "订单号")
    private String orderCode;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "GetOrderDetailRequestData{" +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
