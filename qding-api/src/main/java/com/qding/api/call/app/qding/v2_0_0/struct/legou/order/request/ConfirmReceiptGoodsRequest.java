package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/20.
 */
@Validate
public class ConfirmReceiptGoodsRequest extends BaseRequest {

    private static final long serialVersionUID = 7989202077934828502L;

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
        return "ConfirmReceiptGoodsRequest{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }
}
