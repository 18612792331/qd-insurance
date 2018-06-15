package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/6/22.
 */
@Validate
public class GetEvaluateUserDetailByOrderCodeRequest extends BaseRequest {

    private static final long serialVersionUID = -4748228486899273166L;

    @ExplainAnnotation (explain = "订单号")
    @NotNullValidate
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "GetEvaluateUserDetailByOrderCodeRequest{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }
}
